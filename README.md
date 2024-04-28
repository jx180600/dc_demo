一、结构说明
├── src/main/java // API 接口

│ ├── bean

│ │ ├──// API接口需要使用到的一些数据实体

│ ├── constant

│ │ ├──// API接口的常量（例如URL、appKey等）

│ └── API接口的关键逻辑Demo

├── README.md

二、Demo说明
    1、LoginDemo为登录Demo
    2、BoxPremiumInfoDemo为获取航次箱体保险配置信息demo
    3、BookingDemo为订舱Demo
    4、BillChangeDemo为改单Demo

三、使用说明
    将本公司申请到的appKey以及appSecret，替换到BaseConstants类中，调用Demo中的main方法即可完成调用。
    实际应用中，各接口参数根据实际业务场景填写即可。
