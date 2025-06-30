console.log("this is script")
const toggleSidebar = () => {
    if ($(".sidebar").is(":visible")) {
        // true — band karna hai
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
    } else {
        // false — show karna hai
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "20%");
    }
};

// Hide alert after 3 seconds
window.addEventListener('DOMContentLoaded', function() {
    setTimeout(function() {
        var alert = document.querySelector('.alert');
        if(alert){
            alert.style.display = 'none';
        }
    }, 1000);
});
