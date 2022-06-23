import './App.css';
import {Component} from "react";

class App extends Component {
  state = {
    items: []
  };

  async componentDidMount() {
    const response = await fetch('/api/items');
    const body = await response.json();
    this.setState({items: body});
  }

  render() {
    const {items} = this.state;
    return (
        <div className="App">
          <h1>Item List</h1>
            {items.map(item =>
                <div key={item.id}>
                  {item.title} ({item.description})
                </div>
            )}
        </div>
    );
  }
}
export default App;