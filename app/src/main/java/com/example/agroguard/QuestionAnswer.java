package com.example.agroguard;

public class QuestionAnswer {
    public static String[] question = {
            "Que tipo de dano você observa nas folhas?",
            "Qual parte da planta está mais afetada?",
            "Que tipo de inseto você viu na plantação?"
    };

    public static String[][] choices = {
            {"Manchas amarelas", "Buracos nas folhas", "Folhas enroladas", "Folhas secas"},
            {"Folhas", "Raízes", "Caule", "Frutos"},
            {"Pequenos e verdes", "Grandes e pretos", "Minúsculos e brancos", "Não vi nenhum inseto"}
    };

    public static String[][] possiblePests = {
            {"Pulgão", "Lagarta", "Mosca-branca", "Ácaro"},
            {"Nematóide", "Gafanhoto", "Cochonilha", "Formiga"},
            {"Besouro", "Lagarta-do-cartucho", "Percevejo", "Vaquinha"}
    };

}
