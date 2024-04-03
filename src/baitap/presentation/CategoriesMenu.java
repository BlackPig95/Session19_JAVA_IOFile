package baitap.presentation;

import baitap.business.implementation.CategoriesManagement;
import baitap.config.InputMethods;

public class CategoriesMenu
{
    private static final CategoriesManagement categoriesManagement = new CategoriesManagement();

    public void displayCategoriesMenu()
    {
        while (true)
        {
            System.out.println("************CATEGORIES MENU************\n" +
                    "1. Nhập thông tin các danh mục\n" +
                    "2. Hiển thị thông tin các danh mục\n" +
                    "3. Cập nhật thông tin danh mục\n" +
                    "4. Xóa danh mục\n" +
                    "5. Cập nhật trạng thái danh mục\n" +
                    "6. Thoát\n");
            System.out.println("Nhập lựa chọn theo danh sách trên");
            int choice = InputMethods.nextInt();
            switch (choice)
            {
                case 1:
                    categoriesManagement.addElement();
                    break;
                case 2:
                    categoriesManagement.displayAllElements();
                    break;
                case 3:
                    categoriesManagement.updateElementInfo();
                    break;
                case 4:
                    categoriesManagement.deleteElement();
                    break;
                case 5:
                    categoriesManagement.updateStatus();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }
}
