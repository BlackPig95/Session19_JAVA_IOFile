package baitap.presentation;

import baitap.config.InputMethods;

public class ShopManagement
{
    private static final CategoriesMenu categoriesMenu = new CategoriesMenu();
    private static final ProductMenu productMenu = new ProductMenu();

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("""
                       ***************SHOP MENU****************
                    1. Quản lý danh mục sản phẩm
                    2. Quản lý sản phẩm
                    3. Thoát
                    """);
            System.out.println("Nhập lựa chọn theo danh sách trên");
            int choice = InputMethods.nextInt();
            switch (choice)
            {
                case 1:
                    categoriesMenu.displayCategoriesMenu();
                    break;
                case 2:
                    productMenu.displayProductMenu();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choice not available");
                    break;
            }
        }
    }
}
