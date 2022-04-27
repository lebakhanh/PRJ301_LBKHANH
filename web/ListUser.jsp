<%-- 
    Document   : ListUser
    Created on : Apr 27, 2022, 3:42:44 AM
    Author     : User
--%>

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
            function doDelete(id) {
                if (confirm("Are u sure to delete user with id=" + id + " ?")) {
                    window.location = "delete-user?id=" + id;
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
                <div class="row">
                    <div class="col-12">
                        <!-- ORDERS TABLE -->
                        <div class="box">
                            <div class="box-header">
                                List Users
                            </div>
                            <div class="box-body overflow-scroll">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Password</th>
                                            <th>Email</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Role</th>
                                            <th>Update</th>
                                            <th>Delete</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.accountlist}" var="p">
                                            <tr>
                                                <td>#${p.id}</td>
                                                <td>
                                                    <div class="order-owner">
                                                        <img src="images/person_1.jpg" alt="user image">
                                                        <span>${p.username}</span>
                                                    </div>
                                                </td>
                                                <td>$ ${p.password}</td>
                                                <td>

                                                    ${p.email}

                                                </td>
                                                <td>
                                                    ${p.phone}
                                                </td>
                                                <td>
                                                    ${p.address}
                                                </td>
                                                <td>

                                                    <c:if test="${p.role}">
                                                        Admin
                                                    </c:if>
                                                    <c:if test="${!p.role}">
                                                        User
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <span class="order-status order-ready">
                                                        <a href="update-user?id=${p.id}">Update</td>
                                                    </span>

                                                </td>
                                                <td>
                                                    <span class="order-status order-ready">
                                                        <a href="#" onclick="doDelete('${p.id}')">Delete</td>
                                                    </span>
                                                </td>


                                            </tr>    
                                        </c:forEach>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END ORDERS TABLE -->
                    </div>
                </div>
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
