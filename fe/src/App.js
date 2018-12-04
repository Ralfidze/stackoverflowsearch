import React, { Component } from 'react';
import './App.css';
import Developer from './Developer'
import SearchComponent from './Search'
import TableComponent from './Table';
import Header from './Header'
const DEFAULT_QUERY = '';

const PATH_BASE = 'http://localhost:8080';
const PATH_SEARCH = '/search';
const PARAM_SEARCH = 'searchValue=';

const url = `${PATH_BASE}${PATH_SEARCH}?${PARAM_SEARCH}${DEFAULT_QUERY}`;
console.log(url);

  const name = 'Rail'
  const user ={
    name
  }

class App extends Component {
  constructor(props){
    super(props);
    this.state = {
      who: user.name,
      result: null,
      searchTerm: DEFAULT_QUERY,
    };

    this.setSearchTopQuestions = this.setSearchTopQuestions.bind(this);
    this.fetchSearchTopQuestions = this.fetchSearchTopQuestions.bind(this);
    this.onSave = this.onSave.bind(this);
    this.onSearchChange = this.onSearchChange.bind(this);
    this.onSearch = this.onSearch.bind(this);
  }

  setSearchTopQuestions(result){
    this.setState({result});
  }

  fetchSearchTopQuestions(searchTerm){
    fetch(`${PATH_BASE}${PATH_SEARCH}?${PARAM_SEARCH}${searchTerm}`)
      .then(response => response.json())
      .then(result => this.setSearchTopQuestions(result))
      .catch(e => e);
  }

  onSearch(){
    const { searchTerm } = this.state;
    this.fetchSearchTopQuestions(searchTerm);
  }

  onSearchChange(event){
    this.setState({searchTerm: event.target.value})

  }

  onSave(id){
    const updatedList = this.state.result.filter(function isNotId(obj) {
      return obj.objectID === id;
    });
    this.setState({data: updatedList});
    
  }
  render() {
    const { searchTerm, result } = this.state; 
    
      return(
        <div className="page">
            <Header />
            <SearchComponent 
                  value = {searchTerm}
                  onSearch = {this.onSearch}
                  onChange = {this.onSearchChange}
             >
            </SearchComponent>
            <TableComponent
                list = {result}
                pattern = {searchTerm}
            />
        </div>
    );
  }
}

export default App;
