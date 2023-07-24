import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../css/login.css';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const [error, setError] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    localStorage.removeItem("user");
    if (username.trim() === "" || password.trim() === "") {
      setError("Please enter both username and password");
      return; 
  }

    try {
       const response = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password,
      });
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
        navigate('/getAllTodos');
      } 
    } catch (error) {
      setError(`Error: Invalid Username and Password`);
    }
   
  };

  return (
    
    <div className="login-container">
     
      <form onSubmit={handleSubmit} className="login-form">
        <div>
        <h4> Welcome ! Please Login</h4>
        </div>
        <div>
          <label>Username:</label>
          <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} />
        </div>
        <div>
          <label>Password:</label>
          <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <div>
          <input type='submit' value='Login' />
          {error && <p>{error}</p>}
        </div>
      </form>
    </div>
  );
};

export default Login;
