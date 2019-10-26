//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
	  questionArr:[
		  {title:'你的胸围大吗?',child:[{childName:'大',checked:true},{childName:'小',checked:false}]},
		  {title:'你的胆子大吗?',child:[{childName:'大',checked:false},{childName:'中',checked:false},{childName:'小',checked:false}]},
		  {title:'你的离家近愿望实现了吗?',child:[{childName:'有',checked:false},{childName:'没有',checked:false}]},
		  {title:'涨薪的愿望实现了吗?',child:[{childName:'实现了',checked:false},{childName:'没实现，还差一点',checked:false}]},
	  ],
    logs: []
  },
  chooseTab:function(radio){
    //这里是为了将父子类的选中index全部传入
	  console.log(radio.detail.value);
    var checindex=radio.detail.value;
    // strs = str.split(","); //字符分割
    var checkArr = checindex.split(",");
    //这里将字符串转为 int值
    var in1 = parseInt(checkArr[0]);
    var in2 = parseInt(checkArr[1]);

//注意这里是获取的子数组，并实现单选（选中的为true，其他为false）
    var checkNumArr = this.data.questionArr[in1].child;

    for (var i = 0; i < checkNumArr.length;i++){
      checkNumArr[i].checked = (i == in2);
    }
  },
/**
 * 进入评分页，并带值
 */
  gotoEvalute(){
    wx.navigateTo({
      url: '../evaluate/evaluate?id=1'
    })
  },

  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  }
})
