Page({

  /**
   * 页面的初始数据
   */
  data: {
    visibleShareWindow: false,
    actions1: [{
      name: '继续',
    },
    {
      name: '去分享',
      icon: 'share',
      openType: 'share'
    }
    ],
  },
  /**
   * 展示分享弹窗
   */
  showshareWindow() {
    console.log('展开分享弹窗');
    this.setData({
      visibleShareWindow: true
    });
  },
  hideshareWindow() {
    console.log('关闭分享弹窗');
    this.setData({
      visibleShareWindow: false
    });
  },
  contuineOpera(target) {
    console.log(target);
    //获得index  用来找寻 标记的对象
    console.log(target.detail.index);
    this.hideshareWindow();
  },
  /**
   * 进入我的页面
   */
  gotoMine(){
    wx.navigateTo({
      url: '../user/user?uId=1',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) { },
  exit: function (e) { },
  resetpwd: function (e) { },
  setemail: function (e) { }
})