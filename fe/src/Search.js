import React from 'react';
function Search(props) {
    const { value, onChange, children, onSearch } = props;

    return (
        <div className="interactions">
            <form>
                {children}
                <input
                    type="text"
                    value={value}
                    onChange={onChange}
                    placeholder="What are you looking for?"
                />
                <input
                    type="button"
                    value="Search"
                    onClick={onSearch}
                />
            </form>
        </div>
    );
}

export default Search;