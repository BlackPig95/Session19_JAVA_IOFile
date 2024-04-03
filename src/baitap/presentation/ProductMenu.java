package baitap.presentation;

import baitap.business.design.IProduct;
import baitap.business.implementation.ProductManagement;
import baitap.config.InputMethods;

public class ProductMenu
{
    private static final IProduct productManagement = new ProductManagement();

    public void displayProductMenu()
    {
        while (true)
        {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Thoát\n");
            System.out.println("Nhập lựa chọn theo danh sách trên");
            int choice = InputMethods.nextInt();
            switch (choice)
            {
                case 1:
                    productManagement.addElement();
                    break;
                case 2:
                    productManagement.displayAllElements();
                    break;
                case 3:
                    productManagement.sortByPrice();
                    break;
                case 4:
                    productManagement.updateElementInfo();
                    break;
                case 5:
                    productManagement.deleteElement();
                    break;
                case 6:
                    productManagement.searchByName();
                    break;
                case 7:
                    productManagement.searchInPriceRange();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không khả dụng");
                    break;
            }
        }
    }
}
