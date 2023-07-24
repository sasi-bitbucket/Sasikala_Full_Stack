import { useNavigate } from 'react-router-dom';

function Logout() {
  const navigate = useNavigate();

  localStorage.clear(); // clear the localStorage
  navigate('/'); 
}

export default Logout;
