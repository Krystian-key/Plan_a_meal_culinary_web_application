<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%--has been added.--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Zaplanuj Jedzonko</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="/KRA_SJE_W_07_ScrumLab_war_exploded/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

</head>
<body>
<%@include file="header.jsp"%>
<form action="/app/recipe/add" method="Post">
    <div>
        <table>
            <thead>

            </thead>
            <tbody>
            <tr>
                <th>
                    Nazwa przepisu:
                </th>
                <th>
                    <input type="text" name="recipeName"><br>
                </th>
            </tr>
            <tr>
                <th>
                    Opis przepisu:
                </th>
                <th>
                    <input type="text" name="recipeDescription"><br>
                </th>
            </tr>
            <tr>
                <th>
                   Czas przygotowania:
                </th>
                <th>
                    <input type="text" name="cookTime"><br>
                </th>
            </tr>
            <tr>
                <th>
                    Czas przygotowania:
                </th>
                <th>
                    <input type="number" name="cookTime"><br>
                </th>
            </tr>
            <tr>
                <th>
                    Sposób przygotowania:
                </th>
                <th>
                    <input type="textarea" name="wayToCook"><br>
                </th>
            </tr>
            <tr>
                <th>
                    Sposób przygotowania:
                </th>
                <th>
                    <input type="textarea" name="wayToCook"><br>
                </th>
            </tr>
            <tr>
                <th>
                    Składniki:
                </th>
                <th>
                    <input type="textarea" name="ingredients"><br>
                </th>
            </tr>
            <tr>
                <th>
                </th>
                <th>
                    <input type="submit" value="Wyślij"><br>
                </th>
            </tr>
            </tbody>
        </table>
    </div>

</form>
</body>
</html>