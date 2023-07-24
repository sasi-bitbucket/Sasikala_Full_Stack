import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './component/Login';
import Todo from './component/Todo';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/getAllTodos" element={<Todo />} />
        {/* More routes... */}
      </Routes>
    </Router>
  );
};

export default App;
