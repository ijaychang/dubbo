# 服务发布
## 涉及到的关键类 
ServiceBean,ProxyFactory,AbstractProxyInvoker
## 服务发布触发事件
触发的事件是ContextRefreshedEvent
缘起在com.alibaba.dubbo.config.spring.ServiceBean.onApplicationEvent()
## 服务发布流程
服务发布
ServiceBean.onApplicationEvent()
    ->ServiceConfig.export()
        ->doExport()
            ->doExportUrls()
                ->loadRegistries()
                  doExportUrlsFor1Protocol() //组装url
                    ->  exportLocal(url) //暴露本地服务
                        Exporter<?> exporter = protocol.export(proxyFactory.getInvoker(ref, (Class) interfaceClass, local));
                        // INFO config.AbstractConfig:  [DUBBO] Export dubbo service com.alibaba.dubbo.config.spring.api.DemoService to local registry, dubbo version: 2.0.0, current host: 172.18.14.161
                        
                        Invoker<?> invoker = proxyFactory.getInvoker(ref, (Class) interfaceClass, url);
                        DelegateProviderMetaDataInvoker wrapperInvoker = new DelegateProviderMetaDataInvoker(invoker, this);
                        Exporter<?> exporter = protocol.export(wrapperInvoker);//有两个exporter一个是injvm,还有一个是remote
                        // INFO config.AbstractConfig:  [DUBBO] Export dubbo service com.alibaba.dubbo.config.spring.api.DemoService to url dubbo://172.18.14.161:20881/com.alibaba.dubbo.config.spring.api.DemoService?anyhost=true&application=demo-provider&bind.ip=172.18.14.161&bind.port=20881&dubbo=2.0.0&generic=false&interface=com.alibaba.dubbo.config.spring.api.DemoService&methods=sayName,getBox&owner=world&pid=1348&service.filter=mymock,default&side=provider&timestamp=1661396973813, dubbo version: 2.0.0, current host: 172.18.14.161
                        
                        
                        
