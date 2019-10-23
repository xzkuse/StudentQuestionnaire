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
	  console.log(radio);
  },
  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  }
})
