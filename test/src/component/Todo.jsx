import React from 'react';
import axios from 'axios';
import authHeader from '../services/auth-header';
import jwtDecode from 'jwt-decode';
import '../css/table.css';


class Todo extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
      editId: null,
      editData: {},
      error: null,
      role : []
    };
  }

  async componentDidMount() {
      this.setState({ loading: true });
      
      const user = JSON.parse(localStorage.getItem("user"));
     // console.log('user', user);
      //console.log('accesstoken', user["accessToken"]);
      if (user.accessToken) {
          const decodedToken = jwtDecode(user.accessToken);
          const authorities = decodedToken.authorities || {};
          this.setState({ role: authorities[0].authority });
    }
    try {
      const response = await axios.get('http://localhost:8080/getAllTodos',{ headers: authHeader() });
      this.setState({ data: response.data });
    } catch (error) {
      this.setState({ error: error.message });
    } finally {
      this.setState({ loading: false });
    }
  }

  handleEdit = (id) => {
    const editItem = this.state.data.find((item) => item.todo_id === id);
    this.setState({ editId: id, editData: editItem });
    };
    
  handleSave = async (id) => {
   // this.setState({ loading: true });
    try {
        const response = await axios.put(`http://localhost:8080/updateTodo/${id}`, this.state.editData, { headers: authHeader() });
        this.setState({ data: response.data ,editId: null,
            editData: {}});
    } catch (error) {
      this.setState({ error: error.message });
    } 
  };

  handleChange = (event, fieldName) => {
    this.setState({ editData: { ...this.state.editData, [fieldName]: event.target.value } });
    };
  render() {
    const { data, editId, editData, error,role } = this.state;

    if (error) {
      return <div>Error: {error}</div>;
    }

      return (
        <div>
              <h1 class="centered-text">Todo Table</h1>
      <div class="table-container">
        
        <table>
        <thead>
          <tr>
            <th>Activity Name</th>
            <th>Assigned Staff</th>
             {role === 'ROLE_ADMIN' && <th>Actions</th>}
          </tr>
        </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.todo_id}>
                {editId === item.todo_id ? (
                  <>
                    <td><input type="text" value={editData.activity_name} onChange={(e) => this.handleChange(e, 'activity_name')} /></td>
                    <td><input type="text" value={editData.assigned_staff} onChange={(e) => this.handleChange(e, 'assigned_staff')} /></td>
                    <td>
                     
                        <button onClick={() => this.handleSave(item.todo_id)}>Save</button>
                     
                            </td>
                        </>
                ) : (
                  <>
                    <td>{item.activity_name}</td>
                    <td>{item.assigned_staff}</td>
                    {role === 'ROLE_ADMIN' && <td> {role === 'ROLE_ADMIN' && (
                        <button onClick={() => this.handleEdit(item.todo_id)}>Edit</button>
                      )}
                                             
                    </td>}
                  </>
                )}
              </tr>
            ))}
          </tbody>
        </table>
              </div>
         </div>
    );
  }
}

export default Todo;
