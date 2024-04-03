package baitap.business.implementation;

import baitap.business.design.ICategories;
import baitap.business.entity.Categories;
import baitap.config.InputMethods;
import baitap.config.ReadAndWriteFile;

import java.util.ArrayList;
import java.util.List;

import static baitap.business.implementation.ProductManagement.productList;

public class CategoriesManagement implements ICategories
{
    static List<Categories> categoriesList = new ArrayList<>();

    @Override
    public void addElement()
    {
        System.out.println("How many categories you want to add?");
        int n = Integer.parseInt(InputMethods.nextLine());
        for (int i = 0; i < n; i++)
        {
            Categories newCat = new Categories();
            newCat.inputData(categoriesList, true);
            categoriesList.add(newCat);
            System.out.println("Added to list categories");
        }
        ReadAndWriteFile.writeObjectStream("src/baitap/business/database/categories.txt", categoriesList);
        System.out.println("Successfully added all categories");
    }

    @Override
    public void displayAllElements()
    {
        categoriesList = ReadAndWriteFile.readObjectStream("src/baitap/business/database/categories.txt");
        if (categoriesList.isEmpty())
        {
            System.out.println("No categories currently available");
        }
        categoriesList.forEach(c -> c.displayData());
    }

    @Override
    public void updateElementInfo()
    {
        int indexUpdate = getIndexById();
        try
        {
            if (indexUpdate == -1)
            {
                throw new RuntimeException("Category not found");
            }
        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        outer:
        while (true)
        {
            System.out.println("Choose how you would like to update info");
            System.out.println("1. Update name");
            System.out.println("2. Update description");
            System.out.println("3. Update status");
            System.out.println("4. Update all info");
            System.out.println("0. Return");
            int updateChoice = InputMethods.nextInt();
            switch (updateChoice)
            {
                case 1:
                    categoriesList.get(indexUpdate).setCatalogName(categoriesList, false);
                    System.out.println("Successfully updated name");
                    break;
                case 2:
                    System.out.println("Please enter new description");
                    categoriesList.get(indexUpdate).setDescription(InputMethods.nextLine());
                    System.out.println("Successfully updated description");
                    break;
                case 3:
                    categoriesList.get(indexUpdate).setCatalogStatus(!categoriesList.get(indexUpdate).getCatalogStatus());
                    System.out.println("Successfully changed status");
                    break;
                case 4:
                    System.out.println("Please enter new info");
                    categoriesList.get(indexUpdate).inputData(categoriesList, false);
                    System.out.println("Successfully updated all info");
                    break;
                case 0:
                    break outer;
                default:
                    System.out.println("Choice not available");
                    break;
            }
        }
        ReadAndWriteFile.writeObjectStream("src/baitap/business/database/categories.txt", categoriesList);
    }

    @Override
    public void deleteElement()
    {
        int indexDelete = getIndexById();
        try
        {
            if (indexDelete == -1)
            {
                throw new RuntimeException("Category not found");
            }
        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        try
        {
            if (productList.stream().noneMatch(p -> p.getCatalogId() == categoriesList.get(indexDelete).getCatalogId()))
            {
                categoriesList.remove(indexDelete);
                System.out.println("Successfully deleted");
                ReadAndWriteFile.writeObjectStream("src/baitap/business/database/categories.txt", categoriesList);
            } else
            {
                throw new RuntimeException("Category contains product, can't delete");
            }
        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateStatus()
    {
        int indexUpdate = getIndexById();
        try
        {
            if (indexUpdate == -1)
            {
                throw new RuntimeException("Category not found");
            }
        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        categoriesList.get(indexUpdate).setCatalogStatus(!categoriesList.get(indexUpdate).getCatalogStatus());
        System.out.println("Successfully changed status");
        ReadAndWriteFile.writeObjectStream("src/baitap/business/database/categories.txt", categoriesList);
    }

    @Override
    public int getIndexById()
    {
        if (categoriesList.isEmpty())
        {
            System.out.println("No categories currently available");
            return -1;
        }
        System.out.println("Enter category ID");
        int idSearch = InputMethods.nextInt();
        for (int i = 0; i < categoriesList.size(); i++)
        {
            if (categoriesList.get(i).getCatalogId() == idSearch)
                return i;
        }
        return -1;
    }

}
