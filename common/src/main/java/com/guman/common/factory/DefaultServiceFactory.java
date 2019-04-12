package com.guman.common.factory;


/**
 * @author duanhaoran
 * @since 2019/4/1 5:12 PM
 */
public class DefaultServiceFactory implements ServiceFactory {
    @Override
    public <T> T getService(Class<T> type, String name) {
        return null;
    }

//    public <T> T getService(Class<T> type, String name) {
//        if (type.isInterface() && type.isAnnotationPresent(SPI.class)) {
//            ServiceLoader<T> loader = ServiceLoader.load(type);
//            if (name.equals("")) {
//                name = loader.getDefaultName();
//                if (name != null) {
//                    return loader.getDefault();
//                } else {
//                    return loader.getNames().size() > 0 ? loader.getAdaptive() : loader.getOrDefault((String)loader.getNames().iterator().next());
//                }
//            } else {
//                return name.equals("*") ? loader.getAdaptive() : loader.get(name);
//            }
//        } else {
//            return null;
//        }
//    }
}
