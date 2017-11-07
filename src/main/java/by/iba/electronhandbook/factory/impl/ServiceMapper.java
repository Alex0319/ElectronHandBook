package by.iba.electronhandbook.factory.impl;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.factory.ServiceFactory;
import by.iba.electronhandbook.service.GenericService;
import by.iba.electronhandbook.service.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceMapper implements ServiceFactory{
    final private static Map<String, GenericService<? extends AbstractEntity>> services = new HashMap();

    static {
        services.put("MARK", new MarkServiceImpl());
        services.put("STUDY", new StudyServiceImpl());
        services.put("GROUP", new GroupServiceImpl());
        services.put("STUDENT", new StudentServiceImpl());
        services.put("USER", new UserServiceImpl());
        services.put("PROFESSOR", new ProfessorServiceImpl());
    }

    private static class Holder{
        private final static ServiceMapper INSTANCE = new ServiceMapper();
    }

    public static ServiceMapper getInstance(){
        return ServiceMapper.Holder.INSTANCE;
    }

    @Override
    public GenericService<? extends AbstractEntity> getService(String serviceName) {
        if(services.containsKey(serviceName.toUpperCase()))
            return services.get(serviceName.toUpperCase());
        return null;
    }
}
