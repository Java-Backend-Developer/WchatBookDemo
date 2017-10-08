
##用户相关
### 方法名：用户登录 login

    * login 
        - 相对路径：/app_user/app_login
        - 注释：
        - 参数：
             {"username":String,  *
              "password":String   *
             }
                - 类型：String
                - 约束：notNull
                - 注释：登录账号密码
           
               
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data": {
                 "user":{"id":long,
                        "username":String,
                        "password":String,
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
                 {"id":long *
                 }
              
        - 注释：用户Id
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
                        {"id":long,  *
                        "password":String,
                        "email":String,
                        "mobile":String,
                        "type":int,
                        "status":int
                        "user":{"id":long}
                        "userInfo":{"type":int,
                                    "realName":String
                                    "sex":byte
                                    "picture":{"id":long
                                               },
                                     "status":int
                                   },
                        "role":{"id":long                                
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
            * Json        
            		  {"username":String, *
                        "password":String, *
                        "email":String,  
                        "mobile":String,
                        "userInfo":{"realName":String
                                    "sex":byte
                                    "picture":{"id":long
                                               }
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
               username *
                - 类型：String
                - 约束：notNull
                - 注释：用户名
    
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null             
              }

### 方法名：用户 find

    * login 
        - 相对路径：/app_user/app_user_find
        - 注释：
        - 参数：
            * Json
                       {"id":long,
                        "username":String,
                        "email":String,
                        "mobile":String              
                      }
             - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":  {"id":long,
                        "username":String,
                        "password":String,
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

### 方法名：用户列表 list

    * login 
        - 相对路径：/app_user/app_user_list
        - 注释：
        - 参数：
            * Json
                -      {"id":long,
                        "username":String,
                        "email":String,
                        "mobile":String,
                        "type":int,
                        "status":int
                        "userInfo":{"realName":String
                                    "sex":byte
                                   },
                        "role":{"id":long
                                "permission":[{"id":long
                                              }
                                             ]
                                
                               }
                        "start":int  
                        "pageSize":int 

                      }
    
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":[ {"id":long,
                        "username":String,
                        "password":String,
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

                      }]             
              }



### 方法名：增加角色 add

    * login 
        - 相对路径：/role/add
        - 注释：
        - 参数：
            * Json`        
                 {"name":String, *
                  "type":int,
                  "description":String,
                  "parentId":long,
                  "permission":[{"id:long"}]
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：删除角色 remove

    * login 
        - 相对路径：/role/remove
        - 注释：
        - 参数：
            * Json`        
                 {"id":long,
				  "code":String.
                  "name":String
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：修改角色 update

    * login 
        - 相对路径：/role/update
        - 注释：
        - 参数：
            * Json`        
                 {"id:long, *
                  "name":String,
                  "type":int,
                  "description":String,
                  "parentId":long,
                  "status":int,
                  "permission":[{"id:long"}]
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：get角色 find

    * login 
        - 相对路径：/role/find
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
				  "code":String,
                  "name":String             
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":Role
              }

### 方法名：角色列表 list

    * login 
        - 相对路径：/role/list
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "name":String,
                  "type":int,
                  "code":String,
                  "description":String,
                  "parentId":long,
                  "status":int,
                  "permission":[{"id:long"}],
                  "start":int
                  "pageSize":int             
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":List<Role>
              }


### 方法名：增加权限 add

    * login 
        - 相对路径：/permission/add
        - 注释：
        - 参数：
            * Json`        
                 {"name":String, *
                  "description":String
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：删除权限 remove

    * login 
        - 相对路径：/permission/remove
        - 注释：
        - 参数：
            * Json`        
                 {"id":long,
				  "code":String.
                  "name":String
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：修改权限 update

    * login 
        - 相对路径：/permission/update
        - 注释：
        - 参数：
            * Json`        
                 {"id:long, *
                  "name":String,
                  "description":String,
                  "status":int
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：get权限 find

    * login 
        - 相对路径：/permission/find
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "code":String,
                  "name":String             
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":Permission
              }

### 方法名：权限列表 list

    * login 
        - 相对路径：/permission/list
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "name":String,
                  "code":String,
                  "description":String,
                  "status":int,
                  "start":int
                  "pageSize":int             
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":List<Permission>
              }


## Pet相关
### 方法名：增加标签 add

    * login 
        - 相对路径：/label/add
        - 注释：
        - 参数：
            * Json`        
                 {"name":String, *
                  "description":String
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：删除标签 remove

    * login 
        - 相对路径：/label/remove
        - 注释：
        - 参数：
            * Json`        
                 {"id":long,
				  "code":String.
                  "name":String
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：修改标签 update

    * login 
        - 相对路径：/label/update
        - 注释：
        - 参数：
            * Json`        
                 {"id:long, *
                  "name":String,
                  "description":String,
                  "status":int
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：get标签 find

    * login 
        - 相对路径：/label/find
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "code":String,
                  "name":String             
                 }
              
        - 注释：参数必须有一个，id优先
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":Label
              }


### 方法名：标签列表 list

    * login 
        - 相对路径：/label/list
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "name":String,
                  "code":String,
                  "description":String,
                  "status":int,
                  "start":int
                  "pageSize":int             
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":List<Label>
              }

### 方法名：增加宠物 add

    * login 
        - 相对路径：/pet/add
        - 注释：
        - 参数：
            * Json`        
                 {"name":String, *
                  "type":int,
                  "description":String,
                  "labels":[{"id":long}],
                  "pictures":[{"id":long}]
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：删除宠物 remove

    * login 
        - 相对路径：/pet/remove
        - 注释：
        - 参数：
            * Json`        
                 {"id":long
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }

### 方法名：修改宠物 update

    * login 
        - 相对路径：/pet/update
        - 注释：
        - 参数：
            * Json`        
                 {"id:long, *
                  "name":String,
                  "type":int,
                  "description":String,
                  "labels":[{"id":long}],
                  "pictures":[{"id":long}],
                  "status":int
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":null
              }


### 方法名：get宠物 find

    * login 
        - 相对路径：/pet/find
        - 注释：
        - 参数：
            * Json`        
                 {"id:long            
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":Pet
              }


### 方法名：宠物列表 list

    * login 
        - 相对路径：/pet/list
        - 注释：
        - 参数：
            * Json`        
                 {"id:long,
                  "name":String,
                  "type":int,
                  "description":String,
                  "labels":[{"id":long}],
                  "pictures":[{"id":long}],
                  "status":int,
                  "start":int
                  "pageSize":int             
                 }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":List<Pet>
              }

##公用
### 方法名：图片上传 upload

    * login 
        - 相对路径：/picture/upload
        - 注释：
        - 参数：
            * MultipartFile file *
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":id:Long
              }
### 方法名：图片下载 download

    * login 
        - 相对路径：/picture/download
        - 注释：
        - 参数：
              {"id":Long *
              }
              
        - 注释：
        - 返回结果：Json`        
             {"ret" : String
              "msg" :String
              "data":
              }
