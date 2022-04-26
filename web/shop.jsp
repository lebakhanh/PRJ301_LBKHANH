<%-- 
    Document   : shop.jsp
    Created on : Apr 23, 2022, 10:52:19 PM
    Author     : User
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shoppers &mdash; Colorlib e-Commerce Template</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Mukta:300,400,700"> 
        <link rel="stylesheet" href="fonts/icomoon/style.css">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/owl.theme.default.min.css">


        <link rel="stylesheet" href="css/aos.css">

        <link rel="stylesheet" href="css/style.css">

    </head>
    <body>

        <div class="site-wrap">
            <jsp:include page="module/header.jsp" flush="true"/>

            <div class="bg-light py-3">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12 mb-0"><a href="index">Home</a> <span class="mx-2 mb-0">/</span> <strong class="text-black">Shop</strong></div>
                    </div>
                </div>
            </div>

            <div class="site-section">
                <div class="container">

                    <div class="row mb-5">
                        <div class="col-md-9 order-2">

                            <div class="row">
                                <div class="col-md-12 mb-5">
                                    <div class="float-md-left mb-4"><h2 class="text-black h5">Shop All</h2></div>
                                    <div class="d-flex">
                                        <div class="dropdown mr-1 ml-md-auto">                                       
                                        </div>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-secondary btn-sm dropdown-toggle" id="dropdownMenuReference" data-toggle="dropdown">Reference</button>
                                            <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                                <a class="dropdown-item" href="#">Relevance</a>
                                                <a class="dropdown-item" href="#">Name, A to Z</a>
                                                <a class="dropdown-item" href="#">Name, Z to A</a>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item" href="#">Price, low to high</a>
                                                <a class="dropdown-item" href="#">Price, high to low</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mb-5">
                                <c:forEach items="${requestScope.listbook}" var="b">
                                    <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
                                    <div class="block-4 text-center border">
                                        <figure class="block-4-image">
                                            <a href="shop-single?id=${b.id}"><img src="images/${b.image}" alt="Image placeholder" class="img-fluid"></a>
                                        </figure>
                                        <div class="block-4-text p-4">
                                            <h3><a href="shop-single?id=${b.id}">${b.name}</a></h3>
                                            <p class="mb-0">Finding perfect t-shirt</p>
                                            <p class="text-primary font-weight-bold">$ ${b.price}</p>
                                        </div>
                                    </div>
                                </div>                              
                                </c:forEach>
                                

                                
                               
                                
                            

                            </div>
                            <div class="row" data-aos="fade-up">
                                <div class="col-md-12 text-center">
                                    <div class="site-block-27">
                                        <ul>
                                              <c:if test="${page>1}">
                                            <li><a href="shop?page=${page-1}">&lt;</a></li>
                                              </c:if>
                                              <c:forEach begin="1" end="${requestScope.num}" var="n">
                                              <li class="${n==page?"active":""}" ><span><a href="shop?page=${n}">${n}</a></span></li>
                                              </c:forEach>
                                           
                                            <c:if test="${page<num}">
                                            <li><a href="shop?page=${page+1}">&gt;</a></li>
                                              </c:if>
                                            
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3 order-1 mb-5 mb-md-0">
                            <div class="border p-4 rounded mb-4">
                                <h3 class="mb-3 h6 text-uppercase text-black d-block">Categories Book</h3>
                                <ul class="list-unstyled mb-0">
                                    <c:forEach items="${requestScope.listcate}" var="c">
                                        <li class="mb-1"><a href="shop?cid=${c.id}" class="d-flex"><span>${c.name}</span></a></li>
                                    </c:forEach>
                                </ul>
                            </div>

                          
                        </div>
                    </div>

                  
                    

                </div>
            </div>

            <jsp:include flush="true" page="module/footer.jsp"/>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/jquery-ui.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/aos.js"></script>

        <script src="js/main.js"></script>

    </body>
</html>
