import React from 'react';

const largeColumn = {
    width: '50%'
}
const tinyColumn = {
    width: '7%'
}
const midColumn = {
    width: '30%'
}

const smallColumn = {
    width: '10%'}

const answerGreen = {
    color: 'green'
}

const answerRed = {
    color: 'red'
}

const imageStyle = {
    height: '20px',
    width: '20px',
    marginLeft: '5px'
}

function isSearched(searchTerm){
    return (item) => searchTerm !== '' ? item.title.toLowerCase().includes(searchTerm.toLowerCase()) : ''; 
}
function IsAnswered(props) {
        return (
            <span> 
                {props.value ? 
                    <span style={answerGreen}>yes</span> 
                  : <span style={answerRed}>no</span>}
            </span>
            );
  }
function Table({list, pattern }) {
        if(!list){ return null }; 
        return(
        <div className="table">
            <div className="table-row-header">                
                <span style={tinyColumn} className="table-header App">#</span>
                <span style={largeColumn} className="table-header App">Question</span>
                <span style={smallColumn} className="table-header App">Created</span>
                <span style={tinyColumn} className="table-header App">Answered</span>
                <span style={tinyColumn} className="table-mid-header App">Score</span>
                <span style={midColumn} className="table-header App " >User</span>
            </div>
            {list.filter(isSearched(pattern)).map((item,index) => 
                <div key={item.objectID} className="table-row">
                    <span style={tinyColumn}>
                        {index + 1}
                    </span>
                    <span style={largeColumn}>
                        <a href={item.link} target="_blank">{item.title}</a>
                    </span>
                    <span key={item.objectID} style={smallColumn}>
                            {new Date(item.creation_date*1000).toLocaleDateString()}
                    </span>
                    <span  style={tinyColumn} className="App">
                        <IsAnswered value={item.is_answered}/>
                   </span>
                    <span style={tinyColumn} className="App">
                         {item.score}
                    </span>
                    <span style={midColumn}>
                        {item.owner.display_name}
                        <img src={item.owner.profile_image} style={imageStyle} alt="Owner"/>
                    </span>
                </div>   
            )
            }
        </div>
        );
}

export default Table;