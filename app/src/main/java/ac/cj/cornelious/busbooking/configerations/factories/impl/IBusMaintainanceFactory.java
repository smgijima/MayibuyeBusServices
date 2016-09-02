package ac.cj.cornelious.busbooking.configerations.factories.impl;


import ac.example.mgijima.bustransportingsystem.domain.Maintainance;

/**
 * Created by Cornelious on 4/17/2016.
 */
public interface IBusMaintainanceFactory {
    Maintainance createMaintainance(Long id, String maintainanceCode, String descrition, double cost);

}
