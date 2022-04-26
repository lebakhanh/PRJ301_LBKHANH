<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>
            Admin Dashboard
        </title>
        <link rel="shortcut icon" href="/images/logo-mb.png" type="image/png">
        <!-- GOOGLE FONT -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <!-- BOXICONS -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- APP CSS -->
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/app.css">
        <script type="text/javascript">
            function doDelete(id){
                if(confirm("Are u sure to delete product with id="+id+" ?")){
                    window.location="delete?id="+id;
                }
            }
        </script>
    </head>

    <body>

        <jsp:include page="module/header-admin.jsp" flush="true"/>

        <!-- MAIN CONTENT -->
        <div class="main">
            <div class="main-header">
                <div class="mobile-toggle" id="mobile-toggle">
                    <i class='bx bx-menu-alt-right'></i>
                </div>
                <div class="main-title">
                    dashboard
                </div>
            </div>
            <div class="main-content">
                <form action="add-book" method="post">
            
            Book Name:  <input type="text" name="name" placeholder="Name"><br>
            </br>
            Book Price:     <input type="number" name="price" placeholder="Date Of Birth"><br>
            </br>
            Book Quality:       <input type="number" name="quanlity" placeholder="Address"><br>
            </br>
            Book Image:     <input type="text" name="image" placeholder="Address"><br>

            </br>

            Category        
            <select name="cate" onchange="document.getElementById('f').submit()"> 
                <option value="0"> Pls choose a category</option>
                <c:forEach items="${requestScope.catelist}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Submit" >
        </form>
            </div>
        </div>
        <!-- END MAIN CONTENT -->

        <div class="overlay"></div>

        <!-- SCRIPT -->
        <!-- APEX CHART -->
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <!-- APP JS -->
        <script src="js/app.js"></script>

    </body>

</html>