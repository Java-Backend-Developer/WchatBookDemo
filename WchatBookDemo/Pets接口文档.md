### 方法名：用户登录 login

    * login 
        - 相对路径：/app_user/app_login
        - 注释：
        - 参数：
            * username
                - 类型：String
                - 约束：notNull
                - 注释：登录账号
            * password
                - 类型：String
                - 约束：notNull
                - 注释：登录密码
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data": {
                 "user":{"id":long,
                        "username":String,
                        "email":String,
                        "mobile":String,
                        "type":int,
                        "status":int
                        "createdDatetime":Timestamp
                        "updatedDatetime":Timestamp
                        "user":user
                        "userInfo":{"type":int,
                                    "realName":String
                                    "sex":byte
                                    "picture":{"id":long,
                                               "path":String,
                                               "url":String,
                                               "width":String,
                                               "height":String,
	                                           "status":int,
                                               "createdDatetime":Timestamp,
                                               "updatedDatetime":Timestamp,
                                               "user":user
                                               },
                                     "status":int
                                     "createdDatetime":Timestamp
                                     "updatedDatetime":Timestamp
                                     "user":user
                                   },
                        "role":{"id":long
                                "code":String
                                "name":String
                                "description":String
                                "type":int
                                "parentId":long
                                "isLeaf":boolean
                                "status":int
                                "createdDatetime":Timestamp
                                "updatedDatetime":Timestamp
                                "user":user
                                "permission":[{"id":long
                                               "code":String
                                               "name":String
                                               "description":String
                                               "status":int
                                               "createdDatetime":Timestamp
                                               "updatedDatetime":Timestamp
                                               "user":user
                                              }
                                             ]
                                
                               }

                      }
              }





### 方法名：用户登出 loginout

    * login 
        - 相对路径：/app_user/app_login_out
        - 注释：
        - 参数：
            * Json`        
                 {"id":long}
              
        - 注释：userId
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：用户修改 update

    * login 
        - 相对路径：/app_user/app_user_update
        - 注释：
        - 参数：
            * Json`        
                        {"id":long,
                        "username":String,
                        "email":String,
                        "mobile":String,
                        "type":int,
                        "status":int
                        "createdDatetime":Timestamp
                        "updatedDatetime":Timestamp
                        "user":user
                        "userInfo":{"type":int,
                                    "realName":String
                                    "sex":byte
                                    "picture":{"id":long,
                                               "path":String,
                                               "url":String,
                                               "width":String,
                                               "height":String,
	                                           "status":int,
                                               "createdDatetime":Timestamp,
                                               "updatedDatetime":Timestamp,
                                               "user":user
                                               },
                                     "status":int
                                     "createdDatetime":Timestamp
                                     "updatedDatetime":Timestamp
                                     "user":user
                                   },
                        "role":{"id":long
                                "code":String
                                "name":String
                                "description":String
                                "type":int
                                "parentId":long
                                "isLeaf":boolean
                                "status":int
                                "createdDatetime":Timestamp
                                "updatedDatetime":Timestamp
                                "user":user
                                "permission":[{"id":long
                                               "code":String
                                               "name":String
                                               "description":String
                                               "status":int
                                               "createdDatetime":Timestamp
                                               "updatedDatetime":Timestamp
                                               "user":user
                                              }
                                             ]
                                
                               }
                        }
        - 注释：需要用户全部信息及修改信息
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：用户注册 register

    * login 
        - 相对路径：/app_user/app_user_register
        - 注释：
        - 参数：
            * Json`        
            		  {"id":long,
                        "username":String,
                        "email":String,
                        "mobile":String,
                        "type":int,
                        "status":int
                        "createdDatetime":Timestamp
                        "updatedDatetime":Timestamp
                        "user":user
                        "userInfo":{"type":int,
                                    "realName":String
                                    "sex":byte
                                    "picture":{"id":long,
                                               "path":String,
                                               "url":String,
                                               "width":String,
                                               "height":String,
	                                           "status":int,
                                               "createdDatetime":Timestamp,
                                               "updatedDatetime":Timestamp,
                                               "user":user
                                               },
                                     "status":int
                                     "createdDatetime":Timestamp
                                     "updatedDatetime":Timestamp
                                     "user":user
                                   },
                        "role":{"id":long
                                "code":String
                                "name":String
                                "description":String
                                "type":int
                                "parentId":long
                                "isLeaf":boolean
                                "status":int
                                "createdDatetime":Timestamp
                                "updatedDatetime":Timestamp
                                "user":user
                                "permission":[{"id":long
                                               "code":String
                                               "name":String
                                               "description":String
                                               "status":int
                                               "createdDatetime":Timestamp
                                               "updatedDatetime":Timestamp
                                               "user":user
                                              }
                                             ]
                                
                               }

                      }
              
                - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：用户名检测 check

    * login 
        - 相对路径：/app_user/app_user_check
        - 注释：
        - 参数：
            * username
                - 类型：String
                - 约束：notNull
                - 注释：用户名
    
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null             
              }