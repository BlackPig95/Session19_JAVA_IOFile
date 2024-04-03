package baitap.business.design;

import baitap.business.entity.Product;

public interface IProduct extends IGenericDesign
{
    void sortByPrice();

    void searchByName();

    void searchInPriceRange();
}
