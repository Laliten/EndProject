<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2019-01-02
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>切换</title>
    <link rel="stylesheet" href="../../css/userHome/swiper.min.css">
    <link rel="stylesheet" href="../../css/userHome/certify.css">
    <script src="../../js/swiper.min.js"></script>

</head>
<body>
    <div id="certify">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="../../image/userHome/certify01.png" /><p>Picture information 1</p></div>
                <div class="swiper-slide"><img src="../../image/userHome/certify02.png" /><p>Picture information 2</p></div>
                <div class="swiper-slide"><img src="../../image/userHome/certify03.png" /><p>Picture information 3</p></div>
                <div class="swiper-slide"><img src="../../image/userHome/certify04.png" /><p>Picture information 4</p></div>
                <div class="swiper-slide"><img src="../../image/userHome/certify05.png" /><p>Picture information 5</p></div>
            </div>
        </div>
        <div class="swiper-pagination"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
    </div>

    <script>
        var certifySwiper = new Swiper('#certify .swiper-container', {
            watchSlidesProgress: true,
            slidesPerView: 'auto',
            centeredSlides: true,
            loop: true,autoplay:true,
            loopedSlides: 5,
            autoplay: true,
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            pagination: {
                el: '.swiper-pagination',
                //clickable :true,
            },
            on: {
                progress: function(progress) {
                    for (i = 0; i < this.slides.length; i++) {
                        var slide = this.slides.eq(i);
                        var slideProgress = this.slides[i].progress;
                        modify = 1;
                        if (Math.abs(slideProgress) > 1) {
                            modify = (Math.abs(slideProgress) - 1) * 0.3 + 1;
                        }
                        translate = slideProgress * modify * 260 + 'px';
                        scale = 1 - Math.abs(slideProgress) / 5;
                        zIndex = 999 - Math.abs(Math.round(10 * slideProgress));
                        slide.transform('translateX(' + translate + ') scale(' + scale + ')');
                        slide.css('zIndex', zIndex);
                        slide.css('opacity', 1);
                        if (Math.abs(slideProgress) > 3) {
                            slide.css('opacity', 0);
                        }
                    }
                },
                setTransition: function(transition) {
                    for (var i = 0; i < this.slides.length; i++) {
                        var slide = this.slides.eq(i);
                        slide.transition(transition);
                    }
                }
            }
        });
    </script>
</body>
</html>
