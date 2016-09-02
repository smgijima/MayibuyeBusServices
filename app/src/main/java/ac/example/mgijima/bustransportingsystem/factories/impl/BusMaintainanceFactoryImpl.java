package ac.example.mgijima.bustransportingsystem.factories.impl;

import ac.example.mgijima.bustransportingsystem.domain.Maintainance;

/**
 * Created by Cornelious on 4/17/2016.
 */
public class BusMaintainanceFactoryImpl implements IBusMaintainanceFactory {
private BusMaintainanceFactoryImpl objMaintainanceFactory=null;
    public BusMaintainanceFactoryImpl getInstance()
    {
        if (objMaintainanceFactory==null)
            objMaintainanceFactory=new BusMaintainanceFactoryImpl();
        return objMaintainanceFactory;
    }


    @Override
    public Maintainance createMaintainance(Long id,String maintainanceCode,String descrition, double cost) {
        Maintainance objMaintainance = new Maintainance.BuildMaintainance()
                .id(id)
                .code(maintainanceCode)
                .Description(descrition)
                .cost(cost)
                .build();
        return objMaintainance;
    }
}
