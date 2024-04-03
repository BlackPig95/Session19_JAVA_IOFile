package baitap.business.entity;

import baitap.config.InputMethods;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Product implements Serializable
{
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date createdDate;
    private int catalogId;
    private int productStatus;

    public Product()
    {
    }

    public Product(String productId, String productName, float price, String description, Date createdDate, int catalogId, int productStatus)
    {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(List<Product> productList, boolean isAdding)
    {
        validateInputId(productList, isAdding);
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(List<Product> productList, boolean isAdding)
    {
        validateInputName(productList, isAdding);
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice()
    {
        validateInputPrice();
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public int getCatalogId()
    {
        return catalogId;
    }

    public void setCatalogId(List<Categories> categoriesList)
    {
        chooseCatalogId(categoriesList);
    }

    public int getProductStatus()
    {
        return productStatus;
    }

    public void setProductStatus()
    {
        chooseProductStatus();
    }

    public void inputData(List<Categories> categoriesList, List<Product> productList, boolean isAdding)
    {
        if (categoriesList.isEmpty())
        {
            System.out.println("No categories available, please add categories first");
            return;
        }
        validateInputId(productList, isAdding);
        validateInputName(productList, isAdding);
        validateInputPrice();
        System.out.println("Enter product description");
        this.description = InputMethods.nextLine();
        System.out.println("Enter created date");
        this.createdDate = InputMethods.nextDate();
        chooseCatalogId(categoriesList);
        chooseProductStatus();
    }

    public void displayData(List<Categories> categoriesList)
    {
        if (categoriesList.isEmpty())
        {
            System.out.println("No categories available, please add categories first");
            return;
        }
        String catalogName = getProductCatalogName(categoriesList, this.catalogId);
        System.out.printf("Product ID: %-5s | Product name: %-10s | Product price: %-5.2f\n",
                this.productId, this.productName, this.price);
        System.out.printf("Product description: %-20s | Product created date: %2$td/%2$tm/%2$tY\n",
                this.description, this.createdDate);
        System.out.printf("Product catalog: %-20s | Product status: %-20s\n", catalogName,
                this.productStatus == 0 ? "On sale" : (this.productStatus == 1 ? "Out of stock" : "Not for sale"));
        System.out.println("==================================================================================");
    }

    private void validateInputId(List<Product> productList, boolean isAdding)
    {
        String regex = "[CSA].{3}";
        while (true)
        {
            System.out.println("Enter product ID: Start with C/S/A, and has 4 characters in total");
            String id = InputMethods.nextLine();
            if (!id.matches(regex))
            {
                System.out.println("Please enter ID in correct format");
                continue;
            }
            Product currentProduct = productList.stream().filter(p -> p.productId.equals(id)).findFirst().orElse(null);
            if (currentProduct != null)
            {
                if (isAdding)
                {
                    System.out.println("This ID already existed, please enter again");
                    continue;
                }
                if (currentProduct == this)
                { //Duplicate with itself => Allow
                    this.productId = id;
                    break;
                } else //Duplicated with others => not allow
                {
                    System.out.println("This ID already existed, please enter again");
                }
            } else//Current product == null => No duplication
            {
                this.productId = id;
                break;
            }
        }
    }

    private void validateInputName(List<Product> productList, boolean isAdding)
    {
        while (true)
        {
            System.out.println("Enter product name, max lenght 50 characters");
            String name = InputMethods.nextLine();
            if (name.isBlank())
            {
                System.out.println("Name can't be blank, please enter again");
                continue;
            } else if (name.length() > 50)
            {
                System.out.println("Name can only be 50 characters max in length");
                continue;
            }
            Product currentProduct = productList.stream().filter(p -> p.productName.equals(name))
                    .findFirst().orElse(null);
            if (currentProduct != null)
            {
                if (isAdding)
                {
                    System.out.println("This name already existed, please enter again");
                    continue;
                }
                if (currentProduct == this)
                {  //Duplicate with itself => Allow
                    this.productName = name;
                    break;
                } else //Duplicated with others => not allow
                {
                    System.out.println("This name already existed, please enter again");
                }
            } else//Current product == null => No duplication
            {
                this.productName = name;
                break;
            }
        }
    }

    private void validateInputPrice()
    {
        while (true)
        {
            System.out.println("Please enter product price");
            float price = InputMethods.nextFloat();
            if (price <= 0)
            {
                System.out.println("Price must be greater than 0");
            } else
            {
                this.price = price;
                break;
            }
        }
    }

    private void chooseCatalogId(List<Categories> categoriesList)
    {
        if (categoriesList.isEmpty())
        {
            System.out.println("There is currently no categories");
            return;
        }
        System.out.println("List of current available categories");
        //Only display categories that are currently active
        categoriesList.stream().filter(c -> c.getCatalogStatus()).forEach(c -> c.displayData());
        System.out.println("Please choose product category based on id");
        while (true)
        {
            int id = InputMethods.nextInt();
            Categories chosenCat = categoriesList.stream().filter(c -> c.getCatalogId() == id).findFirst().orElse(null);
            if (chosenCat != null)
            {
                this.catalogId = id;
                break;
            } else
            {
                System.out.println("Choice not available, please enter again");
            }
        }
    }

    private void chooseProductStatus()
    {
        while (true)
        {
            System.out.println("Enter product status: 0-On sale, 1-Out of stock, 2-Not for sale");
            int status = InputMethods.nextInt();
            switch (status)
            {
                case 0, 1, 2:
                    this.productStatus = status;
                    return;
                default:
                    System.out.println("Choice not available, please enter again");
                    break;
            }
        }
    }

    private String getProductCatalogName(List<Categories> categoriesList, int catalogId)
    {
        return categoriesList.stream().filter(c -> c.getCatalogId() == catalogId).findFirst().get().getCatalogName();
    }

    @Override
    public String toString()
    {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
