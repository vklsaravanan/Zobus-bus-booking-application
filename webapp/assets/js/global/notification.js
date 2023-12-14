function showNotification(message, colorClass) {
    var notification = document.getElementById('notification');

    // Set the message and color
    notification.textContent = message;
    notification.className = 'notification ' + colorClass;

    // Slide down
    notification.style.top = '16px';

    // After 1500 milliseconds (1.5 seconds), slide up
    setTimeout(function() {
        notification.style.top = '-60px';
    }, 1500);
}
