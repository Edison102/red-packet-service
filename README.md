# red-packet-service
抢红包练习


5个API接口
1.查看所有可抢红包列表
  GET
  /api/red-packet-service/redpacket/active
  参数：无
  
  结果：
  {
    "code": 200,
    "success": true,
    "data": [
        {
            "user": "测试用户6",
            "redPacketId": 1605445738204419750,
            "createTime": "2020-11-15 21:08:58"
        },
        {
            "user": "测试用户7",
            "redPacketId": 1605446105323770208,
            "createTime": "2020-11-15 21:15:05"
        }
    ],
    "message": "操作成功",
    "requestId": "d9efac3d-c950-422c-b9e7-9eb3f0ebb2f7",
    "timestamp": "2020-11-16 20:17:04",
    "exception": null,
    "exceptionClass": null
  }

2.查看所有历史红包记录
  GET
  /api/red-packet-service/redpacket/history
  参数：无
  
  结果： 同1
  
3.创建一个红包
  post
  参数：
    {
    "user":"测试用户8",
    "total": 300,  //总数
    "amount": 56565645  //金额
    }

  结果：
    {
    "code": 200,
    "success": true,
    "data": {
        "redPacketId": 1605527634862592126
    },
    "message": "操作成功",
    "requestId": "56c6afa9-23df-4a09-89d8-c4b797eaa058",
    "timestamp": "2020-11-16 19:53:55",
    "exception": null,
    "exceptionClass": null
  }
  
4.抢红包
  post
  参数：
    {
    "user":"xxx",
    "redPacketId": 1605527606489604392
    }
  
  结果：
    {
    "code": 200,
    "success": false,
    "data": {
      "amount":434
    },
    "message": "操作成功",
    "requestId": "1ee6827f-f8ab-4b1b-a05e-c8c86c7a2bb2",
    "timestamp": "2020-11-16 20:00:40",
    "exception": null,
    "exceptionClass": null
   }
   
 5.红包详情
  GET
  参数：
    ?id=1605527606489604392
   
  结果：
    {
    "code": 200,
    "success": true,
    "data": {
        "remainingNum": 0,
        "remainingAmount": 0,
        "redPacket": {
            "id": 1605527606489604392,
            "user": "测试用户7",
            "amount": 56565645,
            "total": 300,
            "createTime": "2020-11-16 19:53:27"
        },
        "records": [
            {
                "user": "用户0",
                "amount": 51607,
                "time": "2020-11-16 19:58:45"
            },
            {
                "user": "用户1",
                "amount": 131412,
                "time": "2020-11-16 19:58:45"
            },
            ...
       }
   
