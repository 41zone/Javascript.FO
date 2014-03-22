/**
 * @namespace xh.cservice
 */
KISSY.add("xh/cservice/1.0/csdialog",function(S,Node,Lang,Text,CSData,CSBuild,JEditor){
	var $ = Node.all;
	var $ONE = Node.one;
	var $EVT = S.Event;
/**	
 * asdfasd
 			* 客服服务系统下在线客户对话控制表现层，客服对话框构造器
 	* 不错！
	 * @param {Object} options {"name":"hello"} test asdfasd 
	* @history {1.2.5 2013-08-21 jimmy} 禁止输入框执行扩拽功能
	* @history {1.2.4 2013-08-20} 引入domain设置，并且修改编辑器可编辑状态
	* @history {1.2.3 2013-08-19} 添加JEditor编辑器的使用
	* @history {1.2.2 2013-08-16} 添加机器人提问攻略功能，修改排队为0时的用户提示状态
	* @history {1.2.0 2013-08-15} 添加跟踪父页面的锁定
	* @history {1.1.6 2013-08-14} 添加是否最小化窗口的判断
	* @history {1.1.5 2013-08-14} 机器人只能发送纯文本
	* @history {1.1.4 2013-08-13} 新增了指定机器人
	* @history {1.1.3 2013-08-13} 修改保存记录方法，使得再新页面打开
	* @history {1.1.2 2013-08-13} 添加过滤输入方法
	* @history {1.1.1 2013-8-13} 新增新的提示框 alertWindow2
	* @history {1.1.0 2013-8-13} 新增机器人随机构建
	* @history {1.0.3 2013-08-05} 为上传图像处理添加图片大小提示以及队列清空控制
	* @author Jimmy Song
	* @class {public  static} xh.cservice.CSDialog
	* @version 1.2.5
	* @param {int} age 我的年龄
	* @param {xh.cservice.CSDialog} ok
	 */
	var CSDialog = {
		/**
		 * 标签属性列表
		 * @attribute {Object} xh.service.CSDialog.tags
		 */
		tags:{
			container:"#cservice-dialog",
			head:"#csd-header",
			prefix:"#csd-",
			rebotHeader:".csd-rebot-head",
			rebotTransfer:"#csd-transfer-rebot",
			transfer:".csd-transfer",
			section:".section",
			help:".section > .help",
			gap:".section > .gap",
			chat:".section > .chat",
			dialog:".section > .chat > .dialog",
			editor:".section > .chat > .editor",
			info:"#cservice-info",
			send:".cservice-submit > .button-send > .icon-onlysend",
			option:".cservice-submit > .button-send > .icon-option",
			over:".cservice-submit > li > .icon-over",
			clear:'.cservice-plugins > .icon-clear',
			save:'.cservice-plugins > .icon-save',
			uploadImage:'.cservice-plugins > .icon-imageUpload',
			uploadImageInput:'#cservice-uploadImage-human',
			assess:'.cservice-plugins > .icon-assess',
			assessDialog:'#cservice-assess-human',
			assessText:"#cservice-assess-human-text",
			assessSubmit:'#cservice-assess-human-submit',
			textInput:".cservice-chat > .editor > .text",
			
			bubble:"#cservice-bubbles-",
			text:"#cservice-text-",
			bubbleDialog:"#cservice-bubbles-dialog-",
			bubbleCell:".cservice-bubbles-cell",
			quick:"#cservice-quick-",
			
			rebot:"#cservice-rebot",
			human:"#cservice-human",
			submit:"#cservice-submit-",
			
			mail:"#cservice-mail",
			
			tip:"#cservice-tip-",
			wait:"#cservice-wait-",
			
			commonQuestion:'.cservice-common-question',
			questionList:".cservice-question > li > a",
			speech:".cservice-speech",
			questionRebot:"#cservice-question-rebot-",
			
			inputTip:"#cservice-input-tip-"
		},
		uploadEnabled:false,
		editors:{},
		/**
		 * 初始化
		 * @method {public static} xh.cservice.CSDialog.init
		 */
		init:function() {
			//设定父级关系对象
			this.initParent();
			//加载当前状态
			this.loadType();
		},
		initComponent:function(){
			//安装自动调整容器大小方法
			this.installResize();
			//自动初始化展示对话容器大小
			this.resizeSection();
			//安装机器人与人工转换事件
			this.installTransfer();
			//安装提交事件
			this.installSubmit();
			//安装结束事件
			this.installOver();
			//安装关闭事件
			this.installClose();
			//安装清空
			this.installClear();
			//安装保存
			this.installSave();
			//安装评价
			this.installAssess();
			//安装输入事件
			this.installText();
			//加载常见问题
			this.installCommonQuestion();
		},
		/**
		 * 设定父级关系对象，只对弹出方式才有效
		 * @method {public static} xh.cservice.CSDialog.initParent
		 */
		initParent:function() {
			var _this = this;
			var timer = false;
			CSData.queryType(function(query){
				var domain = query.domain;
				if(S.isString(domain) && domain.length > 0) {
					document.domain = domain;
				}
			});
			var doInit = function() {
				var parent = window.opener;
				try {
					if(parent && parent.CService && parent.CService.setClientDialog) {
						parent.CService.setClientDialog(window);
						return true;
					}
				} catch(e){
					return false;
				}
				return false;
			};
			var doLock = function(){
				var interval = 200;
				var added = 300;
				var times = 0;
				if(!timer) {
					window.clearInterval(timer);
					timer = null;
				}
				timer =  window.setInterval(function(){
					var r = doInit();
					if(r || times >= 50) {
						window.clearInterval(timer);
						timer = null;
						interval = 200;
						times = 0;
						_this.initParent();
					}
					interval += added;
					times ++;
				},interval);
			};
			doInit();
			var parent = window.opener;
			if(parent) {
				try{
					$(parent).on("load",function(){
						doInit();
					});
					$(parent).on("unload",function(){
						doLock();
					});
				} catch(e){
					
				}
			}
		}, 
		/**
		 * 为窗口安装重置大小的事件
		 * @method {public static} xh.cservice.CSDialog.installResize
		 */
		installResize:function(){
			var _this =this;
			var isAsync = S.UA.ie && S.UA.version < 8;
			$(window).on("resize",function() {
				if(isAsync) {
					Lang.async({
						id:"cservice.resize",
						reload:true,
						timer:100,
						fn:function(){
							_this.resizeSection();
							_this.resizeBubbleCell();
						}
					});
				} else {
					_this.resizeSection();
					_this.resizeBubbleCell();
				}
			});
		},
		/**
		 * 重新定义对话容器大小
		 * @method {public static} xh.cservice.CSDialog.resizeSection
		 */
		resizeSection:function() {
			var tags = this.tags;
			var hit_d = S.DOM.viewportHeight ();
			var wid_d = S.DOM.viewportWidth ()
			var mar_h = parseFloat($(tags.section).css("marginTop"))+parseFloat($(tags.section).css("marginBottom"));
			var mar_w = parseFloat($(tags.section).css("marginLeft"))+parseFloat($(tags.section).css("marginRight"));
			var hit_s = hit_d - $(tags.head).height()-mar_h;
			var wid_s = wid_d-mar_w;
			//设置对话框整体高度
			$(tags.dialog).height(hit_d);
			//设置显示部分的高度
			$(tags.section).height(hit_s);
			$(tags.section).width(wid_s);
			//设置高度
			$(tags.help).height(hit_s);
			$(tags.gap).height(hit_s);
			$(tags.chat).height(hit_s);
			//设置宽度
			var wid_chat = wid_s-$(tags.help).outerWidth(true)-$(tags.gap).outerWidth(true)-2;
			$(tags.chat).width(wid_chat);
			//设置左端对话框与编辑器的高度
			var hit_editor = $(tags.editor).height()-2;
			$(tags.dialog).height(hit_s-hit_editor-20);
		},
		/**
		 * 安装机器人与人工转换事件
		 * @method {public static} xh.cservice.CSDialog.installTransfer
		 */
		installTransfer:function() {
			var tags = this.tags;
			var _this =this;
			//点击时进行转换
			$(tags.transfer).on("click",function() {
				var dataTransfer = this.getAttribute("data-transfer");
				var type = dataTransfer == 'human' ? 0 : (dataTransfer == 'rebot' ? 1 :-1); 
				if(type == 1) {
					//安装请求
					_this.installRequest();
				}
				_this.playTransfer(type);
			});
		},
		/**
		 * 无动画切换屏幕
		 * @param {Integer} type 切换的类型，0表示机器人，1表示人工
		 */
		playDialog:function(type) {
			if(type != 0 && type != 1) return ;
			var tags = this.tags;
			if(type == 1) {
				$(tags.prefix+"human").show();
				$(tags.prefix+"rebot").hide();
				$(tags.human).css("zIndex",50);
				$(tags.rebot).css("zIndex",0);
			} else if (type == 0){
				$(tags.prefix+"rebot").show();
				$(tags.prefix+"human").hide();
				$(tags.human).css("zIndex",0);
				$(tags.rebot).css("zIndex",50);
			}
			CSData.setType(type);
		},
		/**
		 * 执行转换操作
		 * @method {public static} xh.cservice.CSDialog.playTransfer
		 * @param {Integer} type 操作类型，type为0或1，分别表示机器人和人工
		 */
		playTransfer:function(type) {
			if(type != 0 && type != 1) return ;
			var tags = this.tags;
			if($(tags.human).isRunning() || $(tags.rebot).isRunning()) return ;
			var width = $(window).width();
			var isHuman = type ==1, isRebot = type == 0;
			if(isHuman) {
				$(tags.prefix+"rebot").animate({opacity:0},{
					duration:0.5,
					complete:function(){
						$(tags.prefix+"rebot").hide();
						$(tags.prefix+"human").animate({opacity:1},{duration:0.5});
						$(tags.prefix+"human").show();
					}
				});
			} else {
				$(tags.prefix+"human").animate({opacity:0},{
					duration:0.5,
					complete:function(){
						$(tags.prefix+"human").hide();
						$(tags.prefix+"rebot").animate({opacity:1},{duration:0.5});
						$(tags.prefix+"rebot").show();
					}
				});
			}
			$(tags.human).animate({left:width/2},{
				duration:0.5,
				complete:function(){
					var zI = isHuman ? 50:0;
					$(tags.human).css("zIndex",zI);
				}
			}).animate({left:0},{
				duration:0.5,
				complete:function(){
					if(!isHuman) return ;
				}
			});
			$(tags.rebot).animate({left:-width/2},{
				duration:0.5,
				complete:function(){
					var zI = isRebot ? 50:0;
					$(tags.rebot).css("zIndex",zI);
				}
			}).animate({left:0},{
				duration:0.5,
				complete:function() {
					if(!isRebot) return ;
				}
			});
			CSData.setType(type);
		},
		/**
		 * 安装提交事件
		 * @method {public static} xh.cservice.CSDialog.installSubmit
		 */
		installSubmit:function(){
			var tags = this.tags;
			var _this = this;
			$(tags.send).attr("title","按Enter键发送消息，Ctrl+Enter换行。");
			$(tags.send).on("click",function(){
				var type = this.parentNode.parentNode.getAttribute("data-type");
				if(type == 'human') _this.closeAssess();
				var editor = _this.editors[type];
				CSBuild.checkSimpleHTML(editor.getBody());
				var html = editor.getHTML();
				var text = editor.getText();
				var body = editor.getBody();
				var v = Text.trim(text);
				if(v.length <= 0 && $(editor.getBody()).all("img").length <= 0) { _this.clearAndToBottom(type); return;}
				var overflow = $(body).attr("data-overflow");
				if(overflow == true || overflow == "true") {
					CSBuild.animateColor(body);
					return;
				}
				if(type == "human") {
					var user = CSData.getClientUser();
					CSData.submitChatHuman({context:html,to:user.clientSocketId,dialogId:user.dialogId},function(data) {
						if(!data.flag) {CSBuild.alertWindow2("消息发送失败了。");return;}
						var chat = CSBuild.buildBubbleHuman(data,true);
						$(tags.bubble+type).append(chat);
						CSData.addUserClientRecord(chat);
						_this.clearAndToBottom(type);
					});
				} else {
					_this.closeQuick();
					var sendTime = new Date().getTime();
					CSData.submitChatRebot({keyword:v},function(data) {
						var result = {question:html,result:data};
						var chat = CSBuild.buildBubbleRebot({text:html,time:sendTime},true);
						$(tags.bubble+type).append(chat);
						CSData.addRebotClientRecord(chat);
						chat = CSBuild.buildBubbleRebot(result,false);
						$(tags.bubble+type).append(chat);
						CSData.addRebotClientRecord(chat);
						_this.clearAndToBottom(type);
					});
				}
			});
		},
		/**
		 * 清空输入框，并且对话框移到最低
		 * @method {public static} xh.cservice.CSDialog.clearAndToBottom
		 * @param {String} type 类型 rebot|human
		 */
		clearAndToBottom:function(type,clear) {
			var tags = this.tags;
			var tid = tags.text+type;
			if(!clear) {
				var editor = this.editors[type].getBody();
				$(editor).html("");
				var limit = $(tid).attr("data-limit");
				$(tid+'-limit').html("还可以输入"+limit+"个字");	
			}
			var height = 0;
			$(tags.bubbleDialog+type).children().each(function(item){
				height += $(item).outerHeight(true);
			});
			$(tags.bubbleDialog+type).scrollTop(height);
			this.resizeBubbleCell();
			CSBuild.buildImageResize(tags.speech,function(img) {
				var max = {width:S.DOM.viewportWidth()*0.9,height:S.DOM.viewportHeight()*0.9};
				var win = CSBuild.previewWindow(img.src,max);
				win.open();
			},function(img){
			});
		},
		/**
		 * 调整单元泡泡间距
		 */
		resizeBubbleCell:function(){
			var tags = this.tags;
			$(tags.bubbleCell).each(function(item){
				var content = $(item).children(".content");
				var head = $(item).children(".head");
				var width = $(item).parent().outerWidth(true);
				var cwidth = content.outerWidth(true);
				var hwidth = head.outerWidth(true);
				
				if(cwidth+hwidth > width) {
					content.width(width-hwidth-20);
				}
				if(S.UA.ie <= 7) content.children(".text").width(content.width()-22);
			});
		},
		/**
		 * 安装结束事件
		 * @method {public static} xh.cservice.CSDialog.installOver
		 */
		installOver:function(){
			var tags = this.tags;
			var _this = this;
			$(tags.over).on("click",function(){
				var type = this.parentNode.parentNode.getAttribute("data-type");
				if(type == 'rebot') {
					CSBuild.confirmWindow("确认要退出在线客服吗？",function(result) {
						if(result) {window.close();}
						return true;
					});
					//window.close();
				} else {
					var enable = this.getAttribute("data-enable");
					var dialog = $(tags.assessDialog);
					var submit = dialog.attr("data-submit");
					var disconnect = dialog.attr("disconnect");
					if(enable && (enable == "false" || enable == false)) {
						if(disconnect == "true" && (!submit || submit == "false")) return ;
						CSBuild.confirmWindow("确认要退出在线客服吗？",function(result) {
							if(result) {window.close();}
							return true;
						});
						//window.close();
						return ;
					}
					if(submit && (submit == "true" || submit == true)) {
						CSBuild.confirmWindow("确认要退出在线客服吗？",function(result) {
							if(result) {window.close();}
							return true;
						});
						// window.close();
						return ;
					}
					_this.playAssess(true);
				}
			});
		},
		/**
		 * 安装窗口关闭事件监听
		 * @method {public static} xh.cservice.CSDialog.installClose
		 */
		installClose:function() {
			var _this = this;
			var tags = this.tags;
			var min = false;
			window.onblur=function() {
				min = Lang.windowIsMin();
			};
			window.onfocus = function(){
				min = Lang.windowIsMin();
			};
			/*
			window.onbeforeunload = function() {
				if(min) return ;
				var type = CSData.getType();
				if(type == 1) {
					var dialog = $(tags.assessDialog);
					var submit = dialog.attr("data-submit");
					if(submit == true || submit == "true") {
						return "结束对话？";
					}
					_this.playAssess(false);
				}
				return "结束对话？";
			};
			*/
			window.onunload = function() {
				var opener = window.opener;
				if(opener && opener.CService && opener.CService.destroyClientDialog) {
					opener.CService.destroyClientDialog();
				}
				var type = CSData.getType();
				if(type == 1)
					CSData.disconnect();
			};
		},
		/**
		 * 安装清空命令
		 * @method {public static} xh.cservice.CSDialog.installClear
		 * 
 		 */
		installClear:function(){
			var tags = this.tags;
			$(tags.clear).on("click",function(){
				var type = this.parentNode.getAttribute("data-type");
				var enable = this.getAttribute("data-enable");
				if(enable == false || enable == "false") return ;
				CSBuild.confirmWindow("确定要清空聊天记录吗？",function(result){
					if(result) {
						$(tags.tip+type).empty();
						$(tags.bubble+type).empty();
					}
					return true;
				});
			});
		},
		/**
		 * 安装保存命令
		 * @method {public static} xh.cservice.CServiceDialog.installSave
		 * @param {String} userId 用户ID
		 */
		installSave:function() {
			var tags = this.tags;
			$(tags.save).each(function(item) {
				var type = item.parent().attr('data-type');
				var saveFrame = CSBuild.buildChatRecordFrame();
				item.append(saveFrame);
				var frame = window.frames[saveFrame.name];
				if(frame) {
					frame.window.getRecord = function() {
						var enable = false;
						$(tags.save).each(function(item){
							var parent = item.parent();
							var t = parent.attr("data-type");
							if(t == type) {
								var e = item.attr("data-enable");
								enable = e;
							}
						});
						var record = type == 'human' ? CSData.getUserClientRecord():CSData.getRebotClientRecord();
						return {enable:enable,text:CSBuild.buildChatRecord(record)};
					};
				}
			});
		},
		/**
		 * 安装图片上传事件
		 * @method {public static} xh.cservice.CSDialog.installUploadImage
		 */
		installUploadImage:function(){
			var tags = this.tags;
			var _this = this;
			var queue = [];
			var fileDialogStart = function(){
				while(queue.length > 0) {
					this.cancelUpload(queue.shift(),false);
				}
			};
			var uploadDialogComplete = function(selected,queued,total) {
				if(!_this.uploadEnabled) {
					CSBuild.alertWindow2("请稍等，还没有与客服连通，暂时无法上传图片。");
					return ;
				} 
				if(selected <= 0) return ;
				if(selected > 1) {
					CSBuild.alertWindow2("只能选择一个图片哦~");
					return ;
				}
				if(selected == 1 && queued == 0) {
					CSBuild.alertWindow2("图片大小只能在200 KB范围内哦，并且只能上传JPG|PNG|GIF|BMP的图片哦~");
					return ;
				}
				var editor = _this.editors['human'];
				var imgLen = $(editor.getBody()).all('img').length;
				if(imgLen >= 1) {
					CSBuild.alertWindow2("一次只能上传张图片哦~");
					return ;
				}
				this.startUpload();
			};
			var uploadStart = function(file) {
				return true;
			};
			var uploadError = function(file,code){
				if(code == -270) return ;
				CSBuild.alertWindow2("好像无法上传~");
			};
			var uploadSuccess = function(file,data,response){
				var status = file.filestatus;
				if(status == SWFUpload.FILE_STATUS.COMPLETE) {
					var v = S.JSON.parse(data);
					if(v.result == 0) {
						_this.editors['human'].append('<img src="'+v.msg+'"/>');
					}
				} else {
					CSBuild.alertWindow2("好像没有上传成功！");
				}
			};
			var fileQueued = function(file) {
				queue.push(file.id);
			};
			var swfconfig = {
				upload_url : CSData.url.flashUpload,
				flash_url:CSData.url.flashUrl,
				file_post_name:'imageFile',
				file_size_limit:'200 KB',
				file_types : "*.jpg;*.gif;*.png;*.bmp",
				file_queue_limit:1,
				post_params:{name:'swfupload'},
				button_placeholder_id : "cservice-uploadImage-human" ,
				button_text: '<span class="uploadFont">上传图片</span>',
				button_text_style:'.uploadFont { font-size: 12;color:#444444;background:none;font-family::"Microsoft YaHei"}',
				button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
				button_text_left_padding: 10,
				button_text_top_padding: 8,
				button_cursor : SWFUpload.CURSOR.HAND,
				button_width: "70",
				button_height: "25",
				prevent_swf_caching : false,
        		preserve_relative_urls : false, 
				upload_error_handler : uploadError,
				upload_success_handler:uploadSuccess,
				upload_start_handler:uploadStart,
				file_dialog_complete_handler:uploadDialogComplete,
				file_queued_handler:fileQueued,
				file_dialog_start_handler:fileDialogStart
			};
			new SWFUpload(swfconfig);
		},
		/**
		 * 安装评价 -> 人工
		 * @method {public static} xh.cservice.CSDialog.installAssess
		 */
		installAssess:function(){
			var tags = this.tags;
			var _this = this;
			$(tags.assessText).on("keyup",function(){
				var limit = this.getAttribute("data-limit");
				limit = typeof limit == undefined ? -1 : limit;
				var text = Text.trim($(this).val());
				var length = text.length;
				var id = "#"+this.id+"-limit";
				if(length <= limit) { 
					$(id).removeClass("overflow");
					$(tags.assessText).attr("data-overflow",false);
					$(id).html("还可以输入"+(limit-length)+"个字");
				} else {
					$(id).addClass("overflow");
					$(tags.assessText).attr("data-overflow",true);
					$(id).html("您已经超出"+(length-limit)+"个字");
				}
			});
			$(tags.bubbleDialog+"human").on('click',function(e){
				e.stopPropagation();
				var dialog = $(tags.assessDialog);
				var disconnect = dialog.attr('disconnect');
				if(disconnect == 'true' || disconnect == true) return ;
				dialog.hide();
			});
			//点击提交
			$(tags.assessSubmit).on("click",function(){
				var overflow = $(tags.assessText).attr("data-overflow");
				if(typeof overflow == undefined){ overflow = false; $(tags.assessText).attr("data-overflow",false);}
				if(overflow == true || overflow == "true") {CSBuild.animateColor(tags.assessText);return ;}
				var radio = $('input[name="cservice-assess-human"]:checked');
				if(radio.length <= 0) {alert("您还没有选择满意程度哦~");return;}
				var value = radio.val();
				var text = Text.trim($(tags.assessText).val());
				//提交
				var dialog = $(tags.assessDialog);
				if(value < 0 && text.length <= 0) {
					CSBuild.animateColor(tags.assessText);
					return ;
				}
				//提交完成后是否要关闭
				var flag = dialog.attr("data-flag");
				var dialogInfo = CSData.getDialogInfo();
				CSData.queryAssessUpdate({level_satisfied:value,reason_unsatisfied:text,dialogId:dialogInfo.dialogId},function(data){
					if(data.code != 200) {
						CSBuild.alertWindow2("提交评价出现问题<br/>code:"+data.code);
					} else {
						CSBuild.alertWindow2("您评价成功了！");
					}
					if(flag == true || flag == "true") {
						window.close();
					}
					//说明已经提交过了
					dialog.attr("data-submit",true);
					dialog.hide();
				});
			});
			//点击评价
			$(tags.assess).on("click",function(e) {
				var enable = this.getAttribute("data-enable");
				if(enable == false || enable == "false") return ;
				_this.playAssess(false);
				e.stopPropagation();
			});
		},
		/**
		 * 显示评价对话框
		 * @method {public static} xh.cservice.CSDialog.playAssess
		 * @param {Boolean} flag 是否评价完成后关闭
		 */
		playAssess:function(flag){
			var tags = this.tags;
			flag = typeof flag == undefined? false : flag;
			var dialog = $(tags.assessDialog);
			dialog.attr("data-flag",flag);
			var submit = dialog.attr("data-submit");
			if(submit && (submit == true || submit == 'true')) {
				CSBuild.alertWindow2("您已经评价过了哦~");
				return ;
			}
			dialog.stop();
			var active = dialog.attr('data-active');
			if(active != undefined && (active == 'true' || active == true)) {
				var left = $(tags.assess).offset().left;
				dialog.css("left",left);
				dialog.fadeIn(0.4);
			}
		},
		/**
		 * 关闭评论对话框
		 * @method {public static} xh.cservice.CSDialog.closeAssess
		 */
		closeAssess:function() {
			var tags = this.tags;
			var dialog = $(tags.assessDialog);
			dialog.fadeOut(0.4);
		},
		/**
		 * 验证输入规则
		 * @method {public static} xh.cservice.CSDialog.validateText
		 * @param {Object} input 输入DOM对象
		 */
		validateText:function(input) {
			var type = input.getAttribute("data-type");
			if(type == 'human') this.closeAssess();
			var limit = input.getAttribute("data-limit");
			limit = typeof limit == undefined ? -1 : limit;
			var text = Text.trim($(input).text());
			var html = $(input).html();
			var length = text.length;
			if(length <= 0 && $(input).all("img").length <= 0) { this.clearAndToBottom(type); return false;}
			var id = "#"+input.id+"-limit";
			if(length <= limit) { 
				$(id).removeClass("overflow");
				$(input).attr("data-overflow",false);
				$(id).html("还可以输入"+(limit-length)+"个字");
				return true;
			} else {
				$(id).addClass("overflow");
				$(input).attr("data-overflow",true);
				$(id).html("您已经超出"+(length-limit)+"个字");
				return false;
			}
		},
		/**
		 * 发送消息
		 * @method {public static} xh.cservice.CSDialog.doSendText
		 * @param {Object} input 输入DOM对象
		 */
		doSendText:function(input){
			var tags = this.tags;
			var _this = this;
			var overflow = $(input).attr("data-overflow");
			var type = input.getAttribute("data-type");
			var text = Text.trim($(input).text());
			var html = $(input).html();
			if(overflow == true || overflow == "true") {
				CSBuild.animateColor(input);
				return;
			}
			if(type == "human") {
				//当enter时表示发表内容
				var user = CSData.getClientUser();
				CSData.submitChatHuman({context:html,to:user.clientSocketId,dialogId:user.dialogId},function(data) {
					if(!data.flag) {CSBuild.alertWindow2("消息发送失败了。");return;}
					var chat = CSBuild.buildBubbleHuman(data,true);
					$(tags.bubble+type).append(chat);
					CSData.addUserClientRecord(chat);
					_this.clearAndToBottom(type);
				});
			} else if(type == "rebot") {
				this.closeQuick();
				var sendTime = new Date().getTime();
				var chat = CSBuild.buildBubbleRebot({text:html,time:sendTime},true);
				$(tags.bubble+type).append(chat);
				CSData.addRebotClientRecord(chat);
				CSData.submitChatRebot({keyword:text},function(data) {
					var result = {question:text,result:data};
					chat = CSBuild.buildBubbleRebot(result,false);
					$(tags.bubble+type).append(chat);
					CSData.addRebotClientRecord(chat);
					_this.installQuestionList();
					_this.clearAndToBottom(type);
				});
			}
		},
		/**
		 * 安装文本快捷键事件
		 * @method {public static} xh.cservice.CSDialog.installText 
		 */
		installText:function(){
			var tags = this.tags;
			var _this = this;
			var zone = false;
			//初始化机器人输入框
			var rebotEditor = new JEditor(tags.text+"rebot",{
				styles:CSData.editor.styles
			});
			rebotEditor.init();
			this.editors["rebot"] = rebotEditor;
			var rebotBody = rebotEditor.getBody();
			rebotBody.setAttribute("data-limit",$(tags.text+"rebot").attr("data-limit"));
			rebotBody.setAttribute("id",$(tags.text+"rebot").attr("id"));
			rebotBody.setAttribute("data-type",$(tags.text+"rebot").attr("data-type"));
			//初始化人工输入框
			var humanEditor = new JEditor(tags.text+"human",{
				styles:CSData.editor.styles
			});
			humanEditor.init();
			this.editors["human"] = humanEditor;
			var humanBody = humanEditor.getBody();
			humanBody.setAttribute("data-limit",$(tags.text+"human").attr("data-limit"));
			humanBody.setAttribute("id",$(tags.text+"human").attr("id"));
			humanBody.setAttribute("data-type",$(tags.text+"human").attr("data-type"));
			var doKeydown = function(e) {
				var enable = this.getAttribute("contenteditable");
				if(!enable || enable == "false") return ;
				var type = this.getAttribute('data-type');
				var editor = _this.editors[type];
				if(Text.ctrl(e,86)) {
					CSBuild.checkSimpleHTML(this);
					return ;
				}
				if(Text.ctrl(e,13)) Text.wrap(undefined,editor.getWindow(),editor.getDocument());
				if(e.which == 0 || e.which == 229) zone = true;
				else zone = false;
			};
			
			var doKeyup = function(e){
				var enable = this.getAttribute("contenteditable");
				if(!enable || enable == "false") return ;
				var input = this;
				var type = this.getAttribute("data-type");
				if(e.which == 17) return ;
				if(Text.ctrl(e,86)) {
					if(type == 'rebot') $(this).html(Text.trim($(this).text()));
				}
				CSBuild.checkSimpleHTML(input);
				$(tags.inputTip+type).html("");
				_this.doEnabledEditor(type,true);
				if(!_this.validateText(input)) return ;
				if(Text.ctrl(e,13)) {
					return ;
				} else if(e.which == 13 && !zone) {
					_this.doSendText(input);
					return ;
				}
			};
			
			$(humanBody).on("drop",function(){
				return false;
			});
			$(rebotBody).on("drop",function(){
				return false;
			});
			
			$(rebotBody).on("keydown",doKeydown);
			$(humanBody).on("keydown",doKeydown);
			
			$(rebotBody).on("keyup",doKeyup);
			$(humanBody).on("keyup",doKeyup);
			//为机器人安装快速查询问题的方案
			$(rebotBody).on("keyup",function(e) {
				var text = Text.trim($(this).text()); 
				if(e.which == 13 || (e.which >= 37 && e.which <= 40) || e.which == 17 || e.which == 8) {
					if(text.length <= 0) _this.closeQuick(); 
					return ;
				}
				_this.closeQuick();
				Lang.async({
					timer:5000,
					id:"cservice.quick.rebot",
					reload:true,
					fn:function() {
						var last = Text.trim(_this.editors['rebot'].getText());
						if(last.length > 0) _this.playQuick(text);
					}
				});
			});
			$(rebotBody).on("mousedown",function(){
				_this.closeQuick();
			});
		},
		/**
		 * 安装问题列表的点击事件
		 * @method {public static} xh.cservice.CSDialog.installQuestionList
		 */
		installQuestionList:function(){
			var tags = this.tags;
			var _this = this;
			var type = "rebot";
			$(tags.questionList).detach("click");
			$(tags.questionList).on("click",function(){
				var pid = $(this).attr('data-id');
				var text = $(this).text();
				CSData.queryQuestionRebot({problemid:pid},function(data) {
					var result = {question:text,result:data};
					var chat = CSBuild.buildBubbleRebot(result,false,true);
					$(tags.bubble+type).append(chat);
					CSData.addRebotClientRecord(chat);
					_this.clearAndToBottom(type);
				});
			});
		},
		/**
		 * 展示快速提取问题 
		 * @method {public static} xh.cservice.CSDialog.playQuick 
		 * @param {String} text 要搜索的关键字
		 */
		playQuick:function(text) {
			var tags = this.tags;
			var _this = this;
			var quick = $(tags.quick+"rebot");
			var type = "rebot";
			CSData.queryQuickRebot({keyword:text},function(data){
				var h = CSBuild.buildQuickRebot(data);
				if(data.length <= 0) return ;
				quick.children("ul").html(h);
				quick.fadeIn(0.4);
				quick.all(".title > a").detach("click");
				quick.all(".title > a").on("click",function() {
					var pid = $(this).attr('data-id');
					var text = $(this).text();
					var sendTime = new Date().getTime();
					var chat = CSBuild.buildBubbleRebot({text:text,time:sendTime},true);
					$(tags.bubble+type).append(chat);
					CSData.addRebotClientRecord(chat);
					CSData.queryQuestionRebot({problemid:pid},function(data) {
						var result = {question:text,result:data};
						chat = CSBuild.buildBubbleRebot(result,false,true);
						$(tags.bubble+type).append(chat);
						CSData.addRebotClientRecord(chat);
						_this.clearAndToBottom(type);
					});
					quick.fadeOut(0.4);
				});
			});
		},
		/**
		 * 关闭快速提取问题 
		 * @method {public static} xh.cservice.CSDialog.closeQuick
		 */
		closeQuick:function(){
			var tags = this.tags;
			$(tags.quick+"rebot").fadeOut(0.4);
		},
		/**
		 * 加载当前状态
		 * @method {public static} xh.cservice.CSDialog.loadType
		 */
		loadType:function() {
			var _this = this;
			var tags = this.tags;
			var win = CSBuild.alertWindow({
				text:"正在拼了命的加载验证，请您一定要耐心的等待哦~",
				button:false
			});
			win.open();
			Lang.async({
				time:2000,
				fn:function(){
					CSData.queryType(function(query) {
						win.setText("要开始验证信息了，马上就ok了呢~");
						CSData.querySessionUser(function(data) {
							var user = {username:null,nickname:null,imgUrl:null,userid:null};
							if(data.code != 200) {
								Lang.extend(user,{username:"客户",nickname:"客户",imgUrl:null,userid:'000000'});
							} else {
								Lang.extend(user,data.content);
							}
							CSData.setSessionUser(user);
							$(tags.container).show();
							_this.initComponent();
							_this.playDialog(CSData.getType());
							if(query.type != CSData.getType()) {
								if(query.animate) {_this.playTransfer(query.type);}
								else {_this.playDialog(query.type);}
							}
							if(query.type == 1) {_this.installRequest();}
							_this.loadRebotConfig(query.rebot);
							CSData.setType(query.type);
							win.setText("正在为您准备视觉大餐，稍等哦~");
							S.ready(function(){
								win.setText("OK！搞定了，感谢您的等待呢~");
								win.close();
							});
						});
					});
				}
			});
		},
		/**
		 * 加载机器人配置，该配置非常重要，决定了机器人的属性
		 * @method {public static} xh.cservice.CSDialog.loadRebotConfig
		 * @param {Integer} type 机器人类型
		 */
		loadRebotConfig:function(type) {
			var tags = this.tags;
			var _this = this;
			type = parseInt(type);
			type = isNaN(type) ? -1 : type;
			var initRebotInfo = function(config){
				var rebotPrefix = tags.prefix+"rebot";
				var rebot = config.rebots[config.current];
				$(rebotPrefix+"-title").html(rebot.name);
				$(tags.rebotHeader).addClass(rebot.type);
				$(rebotPrefix+"-desc").html(rebot.desc);
				$(tags.rebotTransfer).html("转换"+rebot.name);
				$(tags.questionRebot+"name").html(rebot.name+"攻略");
				$(tags.questionRebot+"text").html(config.questionMethod);
			};
			CSData.queryRebotConfig(function(config) {
				CSData.initRebotConfig(config,type);
				if(type != -1 && (type > config.rebots.length-1 || type < 0)) {
					CSBuild.alertWindow2("您指定的机器人不知道为什么好像不存在于这个世界上，我们为您更换了机器人\""+config.rebots[config.current].name+"\"");
				}
				CSData.setRebotConfig(config);
				initRebotInfo(config);
				_this.doSendRebotHello();
				_this.doEnabledEditor("rebot",true);
			});
		},
		/**
		 * 机器人发送欢迎语
		 * @method {public static} xh.cservice.CSDialog.doSendRebotHello
		 */
		doSendRebotHello:function() {
			var tags = this.tags;
			var chat = CSBuild.buildBubbleRebot({result:[CSData.getRebotHello()]},false,true);
			$(tags.bubble+'rebot').append(chat);
			CSData.addRebotClientRecord(chat);
			this.clearAndToBottom('rebot');
		},
		/**
		 * 安装客户端请求事件
		 * @method {private static} xh.cservice.CSDialog.installRequest
		 */
		installRequest:function(){
			var tags = this.tags;
			var _this = this;
			var connect = $(tags.human).attr('connect');
			if(connect == true || connect == "true") return ;
			var html = CSBuild.buildTipHuman('connect');
			$(tags.tip+"human").append(html);
			$(tags.human).attr('connect',true);
			var user = CSData.getSessionUser();
			_this.doEnabledEditor('human',false);
			CSData.requestConnectUser({userId:user.userid,userName:user.username,nickName:user.nickname,headImg:user.imgUrl},function(data) {
				if(data.flag) {
					var wait = CSBuild.buildTipHuman('wait','未知');
					$(tags.tip+"human").append(wait);
					CSData.requestWaitNumbers(function(data) {
						$(tags.wait+'human').html(data);
						if(data <= 0) {
							$(tags.wait+'human').parent().html("现在已经排队到您了，请稍等，客服马上与您联系...");
							CSData.stopTimer("waitNumbers");
						}
					});
				}
			});
			var active = false;
			CSData.requestEventMessage(function(data) {
				if(data.isLast == 1) {
					var d = {type:'server'};
					Lang.extend(d,data);
					var chat = CSBuild.buildTipHuman('disconnect',d);
					$(tags.bubble+'human').append(chat);
					CSData.addUserClientRecord(chat);
					_this.clearAndToBottom('human');
					_this.doEnabledEditor('human',false);
					
					var type = CSData.getType();
					if(type == 1) {
						var dialog = $(tags.assessDialog);
						dialog.attr("disconnect",true);
						var submit = dialog.attr("data-submit");
						if(submit == true || submit == "true") {
							return ;
						}
						_this.playAssess(false);
					}
					return ;
				}
				if(!data.flag) return ;
				_this.doEnabledEditor('human',true);
				CSData.stopTimer("waitNumbers");
				$(tags.wait+'human').html(0);
				var chat = CSBuild.buildBubbleHuman(data,false);
				$(tags.bubble+'human').append(chat);
				CSData.addUserClientRecord(chat);
				_this.clearAndToBottom('human',true);
				//激活当前状态
				if(!active) {
					active = true;
					//安装图片上传事件
					_this.installUploadImage();
					$(tags.assessDialog).attr("data-active",true);
					CSData.setDialogInfo(data);
					CSData.queryAssessSave({dialogId:data.dialogId,clientId:data.fromUserId,userIp:data.userIP,userId:data.toUserId},function(data){
						if(data.code == 200) {}
					});
				}
			});	
			CSData.requestDisconnect(function(data) {
			});
			CSData.requestClientCallReady(function(data) {
				CSData.setClientUser(data);
			});
		},
		/**
		 * 使能编辑器有效性
		 * @method {private static} xh.cservice.CSDialog.doEnabledEditor
 		* @param {String} type 类型，分别是human与rebot
 		* @param {Boolean} flag 有效性
		 */
		doEnabledEditor:function(type,flag) {
			var tags = this.tags;
			//$(tags.text+type).attr("contenteditable",flag);
			var doEnable = function(selector,type,flag){
				$(selector).each(function(item){
					var parent = item.parent("ul");
					var t = parent.attr("data-type");
					if(t == type) item.attr("data-enable",flag);
				});
			};
			var editor = this.editors[type];
			if(editor) editor.setEnable(flag);
			doEnable(tags.save,type,flag);
			doEnable(tags.clear,type,flag);
			doEnable(tags.assess,type,flag);
			doEnable(tags.over,type,flag);
			if(type == "human") {
				if(flag) $(tags.uploadImage).show();
				else $(tags.uploadImage).hide();
				this.uploadEnabled = flag;
			}
			if(flag) {
				$(tags.text+type).removeClass("disabled");
				$(tags.submit+type).removeAttr("disabled");
			} else {
				$(tags.text+type).addClass("disabled");
				$(tags.submit+type).attr("disabled","disabled");
			}
		},
		/**
		 * 加载常见问题
		 * @method {public static} xh.cservice.CSDialog.installCommonQuestion
		 */
		installCommonQuestion:function(){
			var tags = this.tags;
			CSData.queryCommonQuestion(function(data){
				var html = CSBuild.buildCommonQuestion(data);
				$(tags.commonQuestion).html(html);
			});
		}
	};
	return CSDialog;
},{requires:['node','xh/common/1.0/lang','xh/common/1.0/text','../1.0/csdata','../1.0/csbuild','xh/common/1.0/jeditor']});
