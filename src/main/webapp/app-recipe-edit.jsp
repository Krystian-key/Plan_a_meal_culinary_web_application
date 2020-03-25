<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>

<body>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="mt-4 ml-4 mr-4">
                    <div class="row border-bottom border-3">
                        <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                        <form action="${pageContext.request.contextPath}/app/edit/recipe?id=${requestScope.recipe.id}" method="post">
<%--                        <div class="col d-flex justify-content-end mb-2"><input type="submit" value="Edytuj" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">--%>
<%--                        </div>--%>
                        <div class="col d-flex justify-content-end mb-2"><input type="submit" value="Edytuj" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4"><a href="${pageContext.request.contextPath}/app/recipe/list"
                                                                            class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4" style="margin-left: 10px">Powrót</a>
                        </div>
                    </div>

                    <table class="table borderless">
                        <tbody>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Nazwa Przepisu</th>
                            <td class="col-7">
                                <input name="recipeName" type="text" placeholder="${requestScope.recipe.name}">

                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Opis przepisu</th>
                            <td class="col-7">
                                <input name="recipeDescripiton" type="text" placeholder="${requestScope.recipe.description}">
                            </td>
                        </tr>
                        <tr class="d-flex">
                            <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                            <td class="col-7">
                                <input name="recipePreparationTime" type="text" placeholder="${requestScope.recipe.preparationTime}">
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="row d-flex">
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3>
                        </div>
                        <div class="col-2"></div>
                        <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                    </div>
                    <div class="row d-flex">
                        <div class="col-5 p-4">
                            <p>
                                <textarea class="w-100 p-1" rows="9" name="recipePreparation" type="text" placeholder="${requestScope.recipe.preparation}"></textarea>
                            </p>
                        </div>
                        <div class="col-2"></div>
                        <ul class="col-5 p-4 list-unstyled">
                            <textarea class="w-100 p-1" rows="10" name="recipeIngredients" type="text" placeholder="${requestScope.recipe.ingredients}"></textarea>

                        </ul>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>