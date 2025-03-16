package com.omerokumus.feature.product.controller;

import com.omerokumus.feature.product.dto.DtoProduct;
import com.omerokumus.feature.product.dto.DtoProductDetail;

import java.util.List;

public class DummyData {
    public static final List<DtoProduct> products = List.of(
            new DtoProduct(1L, "Lamp", 12.99, "$", "lamp_white_1.webp"),
            new DtoProduct(2L, "Sofa", 29.99, "$", "sofa_white_1.webp"),
            new DtoProduct(2L, "Wardrobe", 49.99, "$", "wardrobe_white_1.webp"),
            new DtoProduct(2L, "Bookcase", 39.99, "$", "bookcase_white_1.webp")
    );

   public static final String lorem1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque tincidunt purus eget fringilla pharetra. Aliquam erat volutpat. Maecenas nec vehicula justo. Vestibulum fermentum luctus lobortis. In ac volutpat erat. Nullam pretium tortor eu porta scelerisque. Morbi euismod urna eu nisi pellentesque, id consequat mauris mollis. Aenean non ligula vel justo vulputate facilisis eget id nibh.";
   public static final String lorem2 = "Nam fringilla blandit arcu, sed dapibus urna commodo vel. Aenean pretium dignissim sapien, vel ultrices lorem finibus quis. Curabitur vehicula sodales ex, vel imperdiet elit tempor nec. Praesent tincidunt massa et massa pulvinar eleifend. Aliquam pulvinar molestie auctor. Nam mattis diam lorem, et tristique libero varius sed. Maecenas vitae vehicula massa. Aliquam eros est, blandit vitae luctus a, volutpat sed libero. Vestibulum blandit a dui sed commodo. Quisque vitae efficitur nibh. Donec nec vulputate leo. Praesent enim arcu, lobortis quis placerat quis, ultrices eu ipsum. Donec aliquam eros ultrices, lacinia eros in, consequat mauris.";
   public static final String lorem3 = "Quisque sagittis magna vitae purus facilisis sollicitudin. Vestibulum a varius est. Aliquam aliquet pellentesque lobortis. Nulla facilisi. Sed bibendum libero ut bibendum ultricies. Proin vulputate, urna sit amet feugiat maximus, nisi elit imperdiet nisi, et molestie dui justo ultricies ipsum. Quisque eget vulputate felis. Ut ut magna nec orci luctus viverra eget at arcu. Duis dui justo, eleifend eu bibendum sed, congue ac diam. Nulla laoreet nisi non nisi hendrerit malesuada. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.";
   public static final String lorem4 = "Donec commodo turpis quis aliquam feugiat. Quisque pellentesque elit non tellus commodo pretium. Vivamus id tellus maximus, mollis enim quis, iaculis felis. Aliquam vel lorem in nibh euismod auctor sed a urna. Integer pulvinar, augue vitae venenatis lobortis, nisl ex accumsan neque, et tempus lectus eros eget nulla. Cras congue bibendum ipsum. Morbi mattis odio vel tellus lobortis mollis. Nunc vel porttitor nisi, in congue velit. Sed non sollicitudin eros.";

   public static final List<String> lampImages = List.of(
           "lamp_white_1.webp",
           "lamp_white_2.webp",
           "lamp_white_3.webp",
           "lamp_blue_1.webp",
           "lamp_blue_2.webp",
           "lamp_black_1.webp",
           "lamp_black_2.webp"
   );

   public static final List<String> sofaImages = List.of(
           "sofa_white_1.webp",
           "sofa_white_2.webp",
           "sofa_white_3.webp",
           "sofa_grey_1.webp",
           "sofa_grey_2.webp",
           "sofa_grey_3.webp"
    );

    public static final List<String> wardrobeImages = List.of(
              "wardrobe_white_1.webp",
              "wardrobe_white_2.webp",
              "wardrobe_white_3.webp",
              "wardrobe_white_4.webp"
     );

    public static final List<String> bookcaseImages = List.of(
              "bookcase_white_1.webp",
              "bookcase_white_2.webp",
              "bookcase_white_3.webp"
     );


    public static final List<String> lampColors = List.of("#ffffff", "#89cff0", "000000");
    public static final List<String> sofaColors = List.of("#ffffff", "#A29FA5");
    public static final List<String> wardrobeColors = List.of("#ffffff");
    public static final List<String> bookcaseColors = List.of("#ffffff");

    public static final List<DtoProductDetail> productDetails = List.of(
            new DtoProductDetail(1L, "Lamp", 12.99, "$", lorem1, List.of(
                    "lamp_white_1.webp",
                    "lamp_white_2.webp",
                    "lamp_white_3.webp",
                    "lamp_blue_1.webp",
                    "lamp_blue_2.webp",
                    "lamp_black_1.webp",
                    "lamp_black_2.webp"
            ), List.of("#ffffff", "#89cff0", "000000")),
            new DtoProductDetail(2L, "Sofa", 29.99, "$", lorem2, List.of(
                    "sofa_white_1.webp",
                    "sofa_white_2.webp",
                    "sofa_white_3.webp",
                    "sofa_grey_1.webp",
                    "sofa_grey_2.webp",
                    "sofa_grey_3.webp"
            ), List.of("#ffffff", "#A29FA5")),
            new DtoProductDetail(3L, "Wardrobe", 49.99, "$", lorem3, List.of(
                    "wardrobe_white_1.webp",
                    "wardrobe_white_2.webp",
                    "wardrobe_white_3.webp",
                    "wardrobe_white_4.webp"
            ), List.of("#ffffff")),
            new DtoProductDetail(4L, "Bookcase", 39.99, "$", lorem4, List.of(
                    "bookcase_white_1.webp",
                    "bookcase_white_2.webp",
                    "bookcase_white_3.webp"
            ), List.of("#ffffff"))
    );


}
