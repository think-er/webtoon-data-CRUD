 // 아코디언 기능을 위한 자바스크립트
var acc = document.getElementsByClassName("accordion");

for (var i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        // 이 버튼의 패널을 선택합니다.
        var panel = this.nextElementSibling;

        // 버튼에 'active' 클래스를 토글하여 화살표 변경
        this.classList.toggle("active");

        // 패널의 display를 토글합니다.
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}
