<template>
<div class="container">
    <div class="header">
        <h1>User Dashboard</h1>
    </div>
    <div class="user-info">
        <h2>User Information</h2>
        <p><strong>Name:</strong> John Doe</p>
        <p><strong>Email:</strong> john@example.com</p>
        <p><strong>Account Type:</strong> Premium</p>
    </div>
    <div class="user-actions">
        <button class="action">Edit Profile</button>
        <button class="action">Change Password</button>
        <button class="action">Logout</button>
    </div>
</div>
</template>



<style>
  body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 1200px;
        margin: 20px auto;
        padding: 20px;
        background-color: #f9f9f9;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .header {
        text-align: center;
        margin-bottom: 20px;
    }
    .user-info {
        background-color: #fff;
        border-radius: 5px;
        padding: 20px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }
    .user-info h2 {
        margin-top: 0;
    }
    .user-info p {
        margin-bottom: 0;
    }
    .user-actions {
        display: flex;
        justify-content: space-around;
        margin-top: 20px;
    }
    .action {
        text-align: center;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .action:hover {
        background-color: #0056b3;
    }
</style>
