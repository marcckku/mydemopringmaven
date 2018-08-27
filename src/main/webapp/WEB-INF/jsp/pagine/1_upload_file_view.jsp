<%-- 
    Document   : 1_upload_file_view
    Created on : 20-mag-2018, 22.42.31
    Author     : Utente
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> 


        <style>

            .carousel {
                position: relative;
            }
            .carousel-inner {
                position: relative;
                width: 100%;
                overflow: hidden;
            }
            .carousel-inner > .item {
                position: relative;
                display: none;
                -webkit-transition: .6s ease-in-out left;
                -o-transition: .6s ease-in-out left;
                transition: .6s ease-in-out left;
            }
            .carousel-inner > .item > img,
            .carousel-inner > .item > a > img {
                line-height: 1;
            }
            @media all and (transform-3d), (-webkit-transform-3d) {
                .carousel-inner > .item {
                    -webkit-transition: -webkit-transform .6s ease-in-out;
                    -o-transition:      -o-transform .6s ease-in-out;
                    transition:         transform .6s ease-in-out;

                    -webkit-backface-visibility: hidden;
                    backface-visibility: hidden;
                    -webkit-perspective: 1000;
                    perspective: 1000;
                }
                .carousel-inner > .item.next,
                .carousel-inner > .item.active.right {
                    left: 0;
                    -webkit-transform: translate3d(100%, 0, 0);
                    transform: translate3d(100%, 0, 0);
                }
                .carousel-inner > .item.prev,
                .carousel-inner > .item.active.left {
                    left: 0;
                    -webkit-transform: translate3d(-100%, 0, 0);
                    transform: translate3d(-100%, 0, 0);
                }
                .carousel-inner > .item.next.left,
                .carousel-inner > .item.prev.right,
                .carousel-inner > .item.active {
                    left: 0;
                    -webkit-transform: translate3d(0, 0, 0);
                    transform: translate3d(0, 0, 0);
                }
            }
            .carousel-inner > .active,
            .carousel-inner > .next,
            .carousel-inner > .prev {
                display: block;
            }
            .carousel-inner > .active {
                left: 0;
            }
            .carousel-inner > .next,
            .carousel-inner > .prev {
                position: absolute;
                top: 0;
                width: 100%;
            }
            .carousel-inner > .next {
                left: 100%;
            }
            .carousel-inner > .prev {
                left: -100%;
            }
            .carousel-inner > .next.left,
            .carousel-inner > .prev.right {
                left: 0;
            }
            .carousel-inner > .active.left {
                left: -100%;
            }
            .carousel-inner > .active.right {
                left: 100%;
            }


            .carousel-control.rightar {
                right: 0;
                left: auto;
                background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, .0001) 0%, rgba(0, 0, 0, .5) 100%);
                background-image:      -o-linear-gradient(left, rgba(0, 0, 0, .0001) 0%, rgba(0, 0, 0, .5) 100%);
                background-image: -webkit-gradient(linear, left top, right top, from(rgba(0, 0, 0, .0001)), to(rgba(0, 0, 0, .5)));
                background-image:    none;
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#00000000', endColorstr='#80000000', GradientType=1);
                background-repeat: repeat-x;
            }
            /* carousel */
            .media-carousel 
            {
                margin-bottom: 0;
                padding: 0 40px 30px 40px;
                margin-top: 30px;
            }
            /* Previous button  */
            .media-carousel .carousel-control.left 
            {
                left: -12px;
                background-image: none;
                background: none repeat scroll 0 0 #a2ab58;
                border: 4px solid #FFFFFF;
                border-radius: 23px 23px 23px 23px;
                height: 40px;
                width : 40px;
                margin-top: 50px
            }

            .media-carousel .carousel-control.leftar 
            {
                left: 0px !important;
                background-image: none;
                background: none repeat scroll 0 0 transparent;
                border: 4px solid transparent;
                height: 60px;
                width: 60px;
                margin-top: 800px;
            }

            /* Next button  */
            .media-carousel .carousel-control.right 
            {
                right: -12px !important;
                background-image: none;
                background: none repeat scroll 0 0 transparent;
                border: 4px solid transparent;
                height: 120px;
                width : 120px;
                margin-top: 80px;
            }


            .media-carousel .carousel-control.rightar 
            {
                right: 0px !important;
                background-image: none;
                background: none repeat scroll 0 0 transparent;
                border: 4px solid transparent;
                height: 60px;
                width: 60px;
                margin-top: 80px;
            }


            /* Changes the position of the indicators */
            .media-carousel .carousel-indicators 
            {
                right: 50%;
                top: auto;
                bottom: 0px;
                margin-right: -19px;
            }
            /* Changes the colour of the indicators */
            .media-carousel .carousel-indicators li 
            {
                background: #c0c0c0;
            }
            .media-carousel .carousel-indicators .active 
            {
                background: #333333;
            }
            .media-carousel img
            {
                width: 100%;
                height: 100%;
            }


            .testimonial{
                background-color: #EBEBEB;
                color:#000;
            }

            .testimonial-heading{
                color:#000;
                text-align:center;
                text-decoration:underline;
            }





            /* video player code here     */



            .gal-container{
                padding: 12px;
            }
            .gal-item{
                overflow: hidden;
                padding: 3px;
            }
            .gal-item .box{
                height: 350px;
                overflow: hidden;
            }
            .box img{
                height: 100%;
                width: 100%;
                object-fit:cover;
                -o-object-fit:cover;
            }
            .gal-item a:focus{
                outline: none;
            }
            .gal-item a:after{
                content:"\e003";
                font-family: 'Glyphicons Halflings';
                opacity: 0;
                background-color: rgba(0, 0, 0, 0.75);
                position: absolute;
                right: 3px;
                left: 3px;
                top: 3px;
                bottom: 3px;
                text-align: center;
                line-height: 350px;
                font-size: 30px;
                color: #fff;
                -webkit-transition: all 0.5s ease-in-out 0s;
                -moz-transition: all 0.5s ease-in-out 0s;
                transition: all 0.5s ease-in-out 0s;
            }
            .gal-item a:hover:after{
                opacity: 1;
            }
            .modal-open .gal-container .modal{
                background-color: rgba(0,0,0,0.4);
            }
            .modal-open .gal-item .modal-body{
                padding: 0px;
            }
            .modal-open .gal-item button.close{
                position: absolute;
                width: 30px;
                height: 30px;
                background-color: #000;
                opacity: 1;
                color: #fff;
                z-index: 999;
                right: -12px;
                top: -12px;
                border-radius: 50%;
                font-size: 15px;
                border: 2px solid #fff;
                line-height: 25px;
                -webkit-box-shadow: 0 0 1px 1px rgba(0,0,0,0.35);
                box-shadow: 0 0 1px 1px rgba(0,0,0,0.35);
            }
            .modal-open .gal-item button.close:focus{
                outline: none;
            }
            .modal-open .gal-item button.close span{
                position: relative;
                top: -3px;
                font-weight: lighter;
                text-shadow:none;
            }
            .gal-container .modal-dialogue{
                width: 80%;
            }
            .gal-container .description{
                position: relative;
                height: 40px;
                top: -40px;
                padding: 10px 25px;
                background-color: rgba(0,0,0,0.5);
                color: #fff;
                text-align: left;
            }
            .gal-container .description h4{
                margin:0px;
                font-size: 15px;
                font-weight: 300;
                line-height: 20px;
            }
            .gal-container .modal.fade .modal-dialog {
                -webkit-transform: scale(0.1);
                -moz-transform: scale(0.1);
                -ms-transform: scale(0.1);
                transform: scale(0.1);
                top: 100px;
                opacity: 0;
                -webkit-transition: all 0.3s;
                -moz-transition: all 0.3s;
                transition: all 0.3s;
            }

            .gal-container .modal.fade.in .modal-dialog {
                -webkit-transform: scale(1);
                -moz-transform: scale(1);
                -ms-transform: scale(1);
                transform: scale(1);
                -webkit-transform: translate3d(0, -100px, 0);
                transform: translate3d(0, -100px, 0);
                opacity: 1;
            }
            @media (min-width: 768px) {
                .gal-container .modal-dialog {
                    width: 55%;
                    margin: 50 auto;
                }
            }
            @media (max-width: 768px) {
                .gal-container .modal-content{
                    height:250px;
                }
            }
            /* Footer Style */
            i.red{
                color:#BC0213;
            }
            .gal-container{
                padding-top :75px;
                padding-bottom:75px;
            }
            footer{
                font-family: 'Quicksand', sans-serif;
            }
            footer a,footer a:hover{
                color: #88C425;
            }




            /* video player code end */


            .test-vid{
                width:100%;
                height:auto;
            }


            .panel-footer-txt > p{
                letter-spacing:10px;
                color:#fff;
                padding-top:5px;

            }
            .panel-footer{
                background-color:#1480D8!important;
            }
            .panel-footer-txt > p:hover{
                color:#a2ab58;
            }


            .checked {
                color: orange;
            }

        </style>


        <title>VIEW FILE UPLOADED</title>
    </head>




    <body>

        <section  class="testimonial">
            <div class="container-fluid">
                <div class="row">


                    <div class="testimonial-heading">
                        <h2>HEADING 1</h2>
                    </div>
                    <p> </p>


                    <div class="container">

                        <div class="row">
                            <div class="col-md-12">



                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="carousel slide media-carousel" id="abc">
                                            <div class="carousel-inner">

                                                <!-- /.slide 1 -->
                                                <div class="item  active">  
                                                    <div class="col-md-6 col-sm-12 col-xs-12">

                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <div class="embed-responsive embed-responsive-16by9">
                                                                    <iframe width="560" height="315" src="https://www.youtube.com/embed/-wE1PF6aYbE" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
                                                                </div>

                                                            </div>

                                                            <div class="panel-footer">
                                                                <div class="panel-footer-txt">
                                                                    <p>John Doe</p>
                                                                    <div style="float:right; margin-top: -25px;">
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    </div>
                                                    <div class="col-md-6 col-sm-12 col-xs-12">

                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <div class="embed-responsive embed-responsive-16by9">
                                                                    <iframe width="560" height="315" src="https://www.youtube.com/embed/26bN6AUIfNA" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
                                                                </div>

                                                            </div>

                                                            <div class="panel-footer">
                                                                <div class="panel-footer-txt">
                                                                    <p>John Doe</p>
                                                                    <div style="float:right; margin-top: -25px;">
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>


                                                <!-- /.slide 2 -->
                                                <div class="item">  

                                                    <div class="col-md-6 col-sm-12 col-xs-12">

                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <div class="embed-responsive embed-responsive-16by9">
                                                                    <iframe width="560" height="315" src="https://www.youtube.com/embed/al2jmNLvmPI" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
                                                                </div>

                                                            </div>

                                                            <div class="panel-footer">
                                                                <div class="panel-footer-txt">
                                                                    <p>John Doe</p>
                                                                    <div style="float:right; margin-top: -25px;">
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    </div>

                                                    <div class="col-md-6 col-sm-12 col-xs-12">

                                                        <div class="panel panel-default">
                                                            <div class="panel-body">
                                                                <div class="embed-responsive embed-responsive-16by9">
                                                                    <iframe width="560" height="315" src="https://www.youtube.com/embed/fhRGUYd8ZRw" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
                                                                </div>

                                                            </div>

                                                            <div class="panel-footer">
                                                                <div class="panel-footer-txt">
                                                                    <p>John Doe</p>
                                                                    <div style="float:right; margin-top: -25px;">
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                        <span class="fa fa-star checked"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>


                                                </div>

                                            </div>
                                            <a data-slide="prev" href="#abc" class="leftar carousel-control" ><img src="https://lh3.googleusercontent.com/DQV4Kju297pbLw-OxHyBU7Stw0iuaJj7zyyLoz8TQetixBNKPeCY-d5FJkzyA-xUztVRKdgOFIGyNsUSlGLMwskym6FRT6DKFFLN-_CptX_VlFZGmtsnK4xqZwTU9Yxvh2kCxwhatdqbzk_Uky3SGei4QVY-zopEgvKW_S5IC5oTcISYOIM1VjCnYcqJJDrw8RrXTEQj2UrWq0_EO0QMiujCzMH2ZxU68SJT1ioueqCI_pkr9v3PJPiFaeByAf7S3FDcbBzC3J-l5HXNtx70UnMSy_tySicFSPJnhVxsci0D9FE_nYugmc6ZEDhG0HsIN6WpHlxGEoBE8Tlhfo4-hQduEIF2n3yJO-gPBzlLpPkp8WuyWPzAFi64X01yOW6djYLSh3UoWje0pLm8FBUCtp5fjYTBn51iCc3ZP06RMdDaMM9abgQDVpM3GMRZqcYMAST02xq6cp1F3TCfqnUkyCjkZnTdED9Ioi5gIljrWhqgBOqcW9HKrBFHibGfZB109kl_SPJpxCOVlUQZ9tqIRsh1tlru-5G4ngtnXFd_cEq0SGdYxe2fSjSkQxFVbdu_VULBFGNSoL0zjVKmTaza48lTQUfRlq1k-_Zdj8Q=s256-no" class="img-responsive" alt="control"></a>
                                            <a data-slide="next" href="#abc" class="rightar carousel-control"><img src="https://lh3.googleusercontent.com/TgD9r34yTa_kofhI58_Rmhpnp87dqstYVcX9ygEROSyUlTcc8gBYrQUcJnIKu3yI4B5pXRd0Vecr2sxbxppP7vKHmYkQQ1YkxeTCqXiAkgxSrH-IgRdCHGdP21rwcZkfGb9dZaQ2sBN9ZEw0pf5hZfpS8GNJ4B9IpOOmMeqkQ22IhhvLAHEPwPieAsPUb1T3K8yi4jLbx-BZWtIMlczqft_Ya6oz_72ub2nkkRHr9USmLH_bjTwjjyca7CyFnxwACa3ANGmfTgNkFOrEZeU6zec0JvM2vvg-TqUDXmLGYGjY6-ZnKaaMi4kOl9d7lzyEC688wjZJ7XQzD9Y6dzttnSyiYX3nxUDAWDkRfDtxhZxV9U-uBYB2C72mPYAFEn8mIq-EZlUlQ28UD2epCQTcbdWlDy2hkMvE75ABzTVJu8DNttfPBY2lX1HzZJedpNJ6VPyYvAIRi5MrsKYb6WKDfaD0Mu8CpGE0fbF6p4qXpJCyk-zUdkJjvqJ8ZLHv-OXwyXkH4tTzsDqdJ9xJWopLcuNLjESwHo_iSVad-S7GgEXZWpAlDBHoy8NO3ikPCmi1t9PRlfdk8AgrZBp1g0YPPeM4mfC2MFwWJsiulFM=s256-no" alt="right arrow" class="img-responsive"></a>
                                        </div>
                                    </div>
                                </div>   



                            </div><!-- /.col-12  end -->
                        </div>  <!-- /.ROW end -->
                    </div>  <!-- /.container end -->

                    <p> </p>



                </div><!-- /. row end -->
            </div><!-- /.container fluid end -->

        </section>


        <script src="//code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!--      
              
          <script type="text/javascript" src="webjars/jquery/1.11.1/jquery.min.js"></script>
          <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      
        -->

    </body>

</html>
