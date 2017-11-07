package by.iba.electronhandbook.factory;

import by.iba.electronhandbook.bean.AbstractEntity;
import by.iba.electronhandbook.service.GenericService;

public interface ServiceFactory{
    GenericService<? extends AbstractEntity> getService(String serviceName);
}
