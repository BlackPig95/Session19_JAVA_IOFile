package baitap.business.implementation;

import baitap.business.design.IProduct;
import baitap.business.entity.Categories;
import baitap.business.entity.Product;
import baitap.config.InputMethods;
import baitap.config.ReadAndWriteFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static baitap.business.implementation.CategoriesManagement.categoriesList;

public class ProductManagement implements IProduct
{
    static List<Product> productList = new ArrayList<>();
    File productFile = new File("src/baitap/business/database/product.txt");

    //Retrieve file data immediately when create instance of ProductManagement
    {
        productList = ReadAndWriteFile.readObjectStream(productFile);
        categoriesList = ReadAndWriteFile.readObjectStream("src/baitap/business/database/categories.txt");
    }

    @Override
    public void addElement()
    {
        System.out.println("How many products you want to add?");
        int n = Integer.parseInt(InputMethods.nextLine());
        for (int i = 0; i < n; i++)
        {
            Product newProduct = new Product();
            newProduct.inputData(categoriesList, productList, true);
            productList.add(newProduct);
            System.out.println("Added to list products");
        }
        System.out.println("Successfully added all products");
        ReadAndWriteFile.writeObjectStream(productFile, productList);
    }

    @Override
    public void displayAllElements()
    {

        if (productList.isEmpty())
        {
            System.out.println("No product currently available");
        }
        productList.forEach(p -> p.displayData(categoriesList));
    }

    @Override
    public void updateElementInfo()
    {
        int indexUpdate = getIndexById();
        try
        {
            if (indexUpdate == -1)
            {
                throw new RuntimeException("Product not found");
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
            System.out.println("1. Update product id");
            System.out.println("2. Update name");
            System.out.println("3. Update price");
            System.out.println("4. Update description");
            System.out.println("5. Update created date");
            System.out.println("6. Update catalog");
            System.out.println("7. Update status");
            System.out.println("8. Update all info");
            System.out.println("0. Return");
            int updateChoice = InputMethods.nextInt();
            switch (updateChoice)
            {
                case 1:
                    productList.get(indexUpdate).setProductId(productList, false);
                    System.out.println("Successfully updated product ID");
                    break;
                case 2:
                    productList.get(indexUpdate).setProductName(productList, false);
                    System.out.println("Successfully updated name");
                    break;
                case 3:
                    productList.get(indexUpdate).setPrice();
                    System.out.println("Successfully updated price");
                case 4:
                    System.out.println("Please enter new description");
                    productList.get(indexUpdate).setDescription(InputMethods.nextLine());
                    System.out.println("Successfully updated description");
                    break;
                case 5:
                    System.out.println("Enter created date");
                    productList.get(indexUpdate).setCreatedDate(InputMethods.nextDate());
                    System.out.println("Successfully updated date");
                    break;
                case 6:
                    productList.get(indexUpdate).setCatalogId(categoriesList);
                    System.out.println("Successfully updated catalog");
                    break;
                case 7:
                    productList.get(indexUpdate).setProductStatus();
                    System.out.println("Successfully changed status");
                    break;
                case 8:
                    System.out.println("Please enter new info");
                    productList.get(indexUpdate).inputData(categoriesList, productList, false);
                    System.out.println("Successfully updated all info");
                    break;
                case 0:
                    break outer;
                default:
                    System.out.println("Choice not available");
                    break;
            }
        }
        ReadAndWriteFile.writeObjectStream(productFile, productList);
    }

    @Override
    public void deleteElement()
    {
        int indexDelete = getIndexById();
        try
        {
            if (indexDelete == -1)
            {
                throw new RuntimeException("Product not found");
            }
        } catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        productList.remove(indexDelete);
        ReadAndWriteFile.writeObjectStream(productFile, productList);
        System.out.println("Successfully deleted product");
    }

    @Override
    public int getIndexById()
    {
        categoriesList = ReadAndWriteFile.readObjectStream("src/baitap/business/database/categories.txt");
        if (productList.isEmpty())
        {
            System.out.println("No product currently available");
            return -1;
        }
        System.out.println("List of products");
        productList.forEach(p -> System.out.println(p.toString()));
        System.out.println("Enter product ID");
        String idSearch = InputMethods.nextLine();
        for (int i = 0; i < productList.size(); i++)
        {
            if (productList.get(i).getProductId().equals(idSearch))
                return i;
        }
        return -1;
    }

    @Override
    public void sortByPrice()
    {
        productList = ReadAndWriteFile.readObjectStream(productFile);
        while (true)
        {
            System.out.println("Enter 1 to sort ascending, 2 to sort descending, 0 to return");
            int choice = InputMethods.nextInt();
            switch (choice)
            {
                case 1:
                    productList.sort((p1, p2) -> Float.compare(p1.getPrice(), p2.getPrice()));
                    productList.forEach(p -> p.displayData(categoriesList));
                    break;
                case 2:
                    productList.sort((p1, p2) -> Float.compare(p2.getPrice(), p1.getPrice()));
                    productList.forEach(p -> p.displayData(categoriesList));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choice not available");
                    break;
            }
        }
    }

    @Override
    public void searchByName()
    {
        productList = ReadAndWriteFile.readObjectStream(productFile);
        System.out.println("Enter product name to search");
        String searchName = InputMethods.nextLine();
        if (productList.stream().noneMatch(p -> p.getProductName().contains(searchName)))
        {
            System.out.println("No product matched");
            return;
        }
        System.out.println("List product that matched your search:");
        productList.stream().filter(p -> p.getProductName().contains(searchName)).forEach(p -> System.out.println(p.toString()));
    }

    @Override
    public void searchInPriceRange()
    {
        productList = ReadAndWriteFile.readObjectStream(productFile);
        System.out.println("From which price you want to search?");
        float fromPrice = InputMethods.nextFloat();
        System.out.println("To which price you want to search?");
        float toPrice = InputMethods.nextFloat();
        if (productList.stream().noneMatch(p -> p.getPrice() >= fromPrice && p.getPrice() <= toPrice))
        {
            System.out.println("No product found");
            return;
        }
        System.out.println("List product that matched your search");
        productList.stream().filter(p -> p.getPrice() >= fromPrice && p.getPrice() <= toPrice).forEach(p -> System.out.println(p.toString()));
    }
}
