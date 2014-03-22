/**
 * @namespace xh.front 
 */
KISSY.add("xh/front/1.0/demo",function(S,Node) {
	/**
	 * 这是window下的对象
	 * @class Tester
	 * @version 1.0 
	 */
	var Tester = {};
	/**
	 * 这只是一种测试而已
	 * @class {public static} xh.front.Front
	 * @author Fronter
	 * @version 1.1.5
	 * @date 2013/08/08
	 * @since 1.0
	 * @deprecated 1.1
	 * @email jimmy.song@aliyun.com
	 * @see http://www.baidu.com/ 
	 */
	var Front = {};
	
	/**
	 * 这只是一种测试而已 ,1.0版本
	 * @class {public static} xh.front.Front
	 * @author Fronter
	 * @version 1.0
	 * @date 2013/08/08
	 * @since 1.0
	 * @deprecated 1.1
	 * @email jimmy.song@aliyun.com
	 * @see http://www.baidu.com/ 
	 */
	Front = {};
	/**
	 * 这是一个测试的类体
	 * @history {1.1 2013-08-20 Jimmy Song} 新增了新方法destroy销毁对象
	 * @history {1.0 2013-08-20 ET} 初始创建
	 * @class {public} xh.front.Demo
	 * @date 2013-08-19
	 * @author Jimmy Song 
	 * @version 1.1
	 * @param {String} selector 选择符
	 */
	var Demo = function(selector){
		/**
		 * Demo中的配置属性
		 * @attribute {public Object} xh.front.Demo.options 
		 */
		this.options = {
			name:undefined
		};
	};
	S.augment(Demo,{
		/**
		 * Demo的初始化方法
		 * @method {public} xh.front.Demo.init
		 */
		init:function(){
			
		},
		/**
		 * Demo的销毁方法，并且可以确定多长时间后可以销毁
		 * @method {public} xh.front.Demo.destroy
		 * @param {int} time 多长时间后销毁 
		 * @return {Boolean} 返回是否销毁
		 * @since 1.1 
		 */
		destroy:function(time){
			return true;
		},
		/**
		 * Demo放弃执行
		 * @method {public} xh.front.Demo.giveUp
		 * @deprecated 1.1 
		 */
		giveUp:function(){}
	});
	return Demo;
},{requires:["node"]});
