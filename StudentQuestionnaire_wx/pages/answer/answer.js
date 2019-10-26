// pages/answer/answer.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

    questionArr: [
      { title: '新项目1？', content: [{ name: '项目一', checked: true }, { name: '项目一', checked: false }, { name: '项目一', checked: false }, { name: '项目一', checked: false }, { name: '项目一', checked: false }, { name: '项目2', checked: false}] },
      { title: '新项目2？', content: [{ name: '项目一', checked: false }, { name: '项目2', checked: false}] },
      { title: '新资产3？', content: [{ name: '项目一', checked: false}, { name: '项目2', checked: false}] }
    ],

    noeIndex:0,
    nowContent:
      { title: '新项目？', content: [{ name: '项目一', checked: false }, { name: '项目2', checked: false}] },

    enableLeft: false,
    enableRight: true,

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      nowContent: this.data.questionArr[this.data.noeIndex]
    });
  },
/**
 * 下一题
 */
  onNextContent(){
    if (!this.data.enableRight) return;
    var nowIndex=this.data.noeIndex;
    nowIndex = nowIndex+1;

    var rightNext = true;
    var leftNext = true;
    if (nowIndex >= this.data.questionArr.length-1){
      rightNext = false;
      leftNext = true;
      nowIndex = this.data.questionArr.length - 1;
    }

    this.setData({
        nowContent: this.data.questionArr[nowIndex],
        noeIndex: nowIndex,
      enableRight: rightNext,
      enableLeft: leftNext
    });

  },
  /**
   * s上一题
   */
  onLastContent() {
    if (!this.data.enableLeft) return;
    var nowIndex = this.data.noeIndex;
    console.log(nowIndex);
    nowIndex = nowIndex - 1;
    console.log(nowIndex);

    var rightNext = true;
    var leftNext = true;
    if (nowIndex < 1) {
      rightNext = true;
      leftNext = false;
      nowIndex = 0;
    }

    this.setData({
      nowContent: this.data.questionArr[nowIndex],
      noeIndex: nowIndex,
      enableRight: rightNext,
      enableLeft: leftNext
    });

  },

/**
 * 单选方法
 */
  checkContent(radio){
    var checkIndex = parseInt(radio.detail.value);
    console.log(checkIndex);
    //这里注意，只是为了能少写点代码所以使用了size，但是修改的还是data种的数据
    var nowContentArr=this.data.nowContent.content;
    for (var i = 0; i < nowContentArr.length;i++){
      this.data.nowContent.content[i].checked = (checkIndex == i);
    }
    console.log(("-------") + this.data.nowContent.content[checkIndex].checked);


    //这里注意，只是为了能少写点代码所以使用了size，但是修改的还是data种的数据
    var allContentArr = this.data.questionArr[this.data.noeIndex].content;
    for (var i = 0; i < allContentArr.length; i++) {
      this.data.questionArr[this.data.noeIndex].content[i].checked = (checkIndex == i);
    }
    console.log(("==========") + this.data.questionArr[this.data.noeIndex].content[checkIndex].checked);
  },
/**
 * 进入列表答题页 
 */
  gotoLogs(){
    wx.navigateTo({
      url: '../logs/logs?id=2',
    })
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