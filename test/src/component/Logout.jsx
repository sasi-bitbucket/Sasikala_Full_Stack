import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/logout.css';

function Logout() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear(); // clear the localStorage
    navigate('/'); // redirect to login page
  };

  return (
    
      <button className='logout-btn' onClick={handleLogout}>Logout</button>
   
  );
}

export default Logout;
