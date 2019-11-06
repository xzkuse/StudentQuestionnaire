// pages/choosetopic/choosetopic.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
	questionArr:[
		{title:"在Intent中ip是由___位字节组成",childArr:[
			{name:"A.8",checked:false},
			{name:"A.16",checked:false},
			{name:"A.32",checked:false},
			{name:"A.64",checked:false},
		]},
		{title:"在2Intent中ip是由___位字节组成",childArr:[
			{name:"A.8",checked:false},
			{name:"A.8",checked:false},
			{name:"A.8",checked:false},
			{name:"A.8",checked:false},
		]},
	],
	currentIndex:0,
  },

	/**
	 * 
	 */
	checkOne:function(target){
    var checkIndex=target.currentTarget.dataset.index;
    console.log("选中了" + this.data.currentIndex+"-->"+checkIndex)
    var quArr = this.data.questionArr;
    var thisChildArr = quArr[this.data.currentIndex].childArr;
    for (var i = 0; i < thisChildArr.length;i++){
      var child=thisChildArr[i];
      child.checked = (i == checkIndex);
    }
    var nextIndex = this.data.currentIndex+1;
    this.setData({
      questionArr: quArr
    });

    var that = this;
    setTimeout(function () {
      that.setData({
        currentIndex: nextIndex
      })
    }, 300);
	},
/**
 * 进入结果页面
 */
gotoResult(){
  wx.navigateTo({
    url: '../chooseresult/chooseresult',
  })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})