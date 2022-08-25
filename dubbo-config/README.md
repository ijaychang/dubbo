# spring自定义命名xml空间解析
```
 ClassPathXmlApplicationContext构造函数
  ->AbstractApplicationContext.refresh()
    ->AbstractApplicationContext.obtainFreshBeanFactory()
      ->AbstractRefreshableApplicationContext.refreshBeanFactory()
        ->AbstractXmlApplicationContext.loadBeanDefinitions()
          ->AbstractXmlApplicationContext.loadBeanDefinitions()
            ->AbstractRefreshableConfigApplicationContext.getConfigLocations()
               ->AbstractBeanDefinitionReader.loadBeanDefinitions(configLocations) [repeat]
                 ->XmlBeanDefinitionReader.doLoadBeanDefinitions()
                   ->XmlBeanDefinitionReader.registerBeanDefinitions()
                     ->XmlBeanDefinitionReader.createReaderContext()
                        -> XmlBeanDefinitionReader.getNamespaceHandlerResolver() 得到命名空间名与NamespaceHandler的映射Map
                          -> XmlBeanDefinitionReader.createDefaultNamespaceHandlerResolver()
                            -> new DefaultNamespaceHandlerResolver(java.lang.ClassLoader)
                               -> new DefaultNamespaceHandlerResolver(classLoader, "META-INF/spring.handlers")
                       DefaultBeanDefinitionDocumentReader.registerBeanDefinitions()
                         ->DefaultBeanDefinitionDocumentReader.doRegisterBeanDefinitions()
                           ->DefaultBeanDefinitionDocumentReader.parseBeanDefinitions()
                             -> 根据delegate.isDefaultNamespace(ele)判断是spring默认的名空间还是自定义的名空间
                               DefaultBeanDefinitionDocumentReader.parseDefaultElement() [spring默认的名空间，即beans]
                               delegate.parseCustomElement(ele) [自定义的名空间]
```
XmlBeanDefinitionReader.getNamespaceHandlerResolver() 得到Xml命名空间Url与NamespaceHandler实现类的映射Map
DefaultBeanDefinitionDocumentReader.doRegisterBeanDefinitions() 完成解析并生成BeanDefinition


AbstractApplicationContext.finishRefresh()发布ContextRefreshedEvent事件

# 服务发布
## 涉及到的关键类 
ServiceBean,ProxyFactory,AbstractProxyInvoker
## 服务发布触发事件
触发的事件是AbstractApplicationContext.finishRefresh()发布的ContextRefreshedEvent事件
监听上下文刷新完成事件是在com.alibaba.dubbo.config.spring.ServiceBean.onApplicationEvent(ContextRefreshedEvent)
## 服务发布流程
服务发布
```
ServiceBean.onApplicationEvent()
    ->ServiceConfig.export()
        ->ServiceConfig.doExport()
            ->ServiceConfig.doExportUrls()
                ->ServiceConfig.doExportUrlsFor1Protocol()
                    ->  ServiceConfig.exportLocal(url) //暴露本地服务
                    Exporter<?> exporter = protocol.export(proxyFactory.getInvoker(ref, (Class) interfaceClass, local));

```
                 INFO config.AbstractConfig:  [DUBBO] Export dubbo service com.alibaba.dubbo.config.spring.api.DemoService to local registry, dubbo version: 2.0.0, current host: 172.18.14.161
                        
                        Invoker<?> invoker = proxyFactory.getInvoker(ref, (Class) interfaceClass, url);
                        DelegateProviderMetaDataInvoker wrapperInvoker = new DelegateProviderMetaDataInvoker(invoker, this);
                        Exporter<?> exporter = protocol.export(wrapperInvoker);//有两个exporter一个是injvm,还有一个是remote
                 INFO config.AbstractConfig:  [DUBBO] Export dubbo service com.alibaba.dubbo.config.spring.api.DemoService to url dubbo://172.18.14.161:20881/com.alibaba.dubbo.config.spring.api.DemoService?anyhost=true&application=demo-provider&bind.ip=172.18.14.161&bind.port=20881&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.config.spring.api.DemoService&methods=sayName,getBox&owner=world&pid=1348&service.filter=mymock,default&side=provider&timestamp=1661396973813, dubbo version: 2.0.0, current host: 172.18.14.161
                        
                        
                        
