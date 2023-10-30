2023年10月18日上传已完成代码  // 写到垃圾回收模块
2023年10月20日上传已完成代码  // 完成政府热线功能
2023年10月23日上传已完成代码  // 完成宠物医院功能

临时token
eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6Ijc2MWFhOGUzLTIwYTQtNDkxMS1iNjMxLTliMGI5NTg3MWRkMCJ9.DmFd61uXozlobxu01rDwPl3G4e3pULAUhGPru8yEPsIEY2jpZPqhuYqlvNgOXlgZh7nl82lKVH-K3emEqG0N7A

开发注意事项 
1.使用implementation fileTree(dir: 'libs', includes: ['*.aar', '*.jar'])加载所有包
2.buildFeatures {
          viewBinding true
          dataBinding true
      }  绑定视图依赖
3.可能因为虚拟机版本问题无法加载出webView 所有使用textView 加载新闻 vb.newsInfoText.text = Html.fromHtml(data.content)

