package baitap.business.entity;

import baitap.config.InputMethods;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Categories implements Serializable
{
    private int catalogId;
    private static int maxId;
    private String catalogName;
    private String description;
    private Boolean catalogStatus;

    public Categories()
    {
    }

    public Categories(String catalogName, String description, Boolean catalogStatus)
    {
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId()
    {
        return catalogId;
    }

//    public void setCatalogId(int catalogId)
//    {
//        this.catalogId = catalogId;
//    }

    public String getCatalogName()
    {
        return catalogName;
    }

    public void setCatalogName(List<Categories> categoriesList, boolean isAdding)
    {
        validateInputName(categoriesList, isAdding);
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getCatalogStatus()
    {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus)
    {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(List<Categories> categoriesList, boolean isAdding)
    {
        if (isAdding)//Won't update Id if not adding
        {
            this.catalogId = setMaxId(categoriesList);
        }
        validateInputName(categoriesList, isAdding);
        System.out.println("Enter catalog description");
        this.description = InputMethods.nextLine();
        System.out.println("Enter catalog status: true-Active, false-Inactive");
        this.catalogStatus = InputMethods.nextBoolean();
    }

    public void displayData()
    {
        System.out.printf("Catalog ID: %d | Catalog name: %s | Catalog description: %s | Catalog status: %s \n",
                this.catalogId, this.catalogName, this.description, (this.catalogStatus ? "Acitve" : "Inactive"));
        System.out.println("=====================================================================================");
    }

    private void validateInputName(List<Categories> categoriesList, boolean isAdding)
    {
        while (true)
        {
            System.out.println("Enter catalog name");
            String name = InputMethods.nextLine();
            if (name.isBlank())
            {
                System.out.println("Name can't be blank, please enter again");
                continue;
            }
            if (name.length() > 50)
            {
                System.out.println("Name can't be longer than 50 characters, please enter again");
                continue;
            }
            //Check if any object with the same name match
            Categories current = categoriesList.stream().
                    filter(c -> c.catalogName.equals(name)).findFirst().orElse(null);
            //current != null => an element matched the name
            if (current != null)
            {   //If adding => Can't be duplicated
                if (isAdding)
                {
                    System.out.println("This name is already used, please enter again");
                    continue;
                }
                //Else => Can duplicate with itself
                if (current == this)
                {   //Name match with itself => Allow
                    this.catalogName = name;
                    break;
                } else //Not match => Duplicating with other products
                {
                    System.out.println("This name is already used, please enter again");
                }
            } else
            {
                //Current == null => No duplication => Can use
                this.catalogName = name;
                break;
            }
        }
    }

    private int setMaxId(List<Categories> categoriesList)
    {
        if (categoriesList.isEmpty())
        {
            return 1;
        }
        return categoriesList.stream().max(Comparator.comparingInt(c -> c.catalogId)).get().catalogId + 1;
    }
}
