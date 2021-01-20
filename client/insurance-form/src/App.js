import './App.css';
import React from 'react'
import axios from 'axios';


export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {currencyId: 'EUR'};

    this.handleCurrChange = this.handleCurrChange.bind(this);
    this.handleAgeOneChange = this.handleAgeOneChange.bind(this);
    this.handleAgeTwoChange = this.handleAgeTwoChange.bind(this);
    this.handleStartDateChange = this.handleStartDateChange.bind(this);
    this.handleEndDateChange = this.handleEndDateChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit(event) {
    event.preventDefault();

    const authToken = 'jwtToken'

    let config = {
      headers: {
        contentType: 'string/json',
        authorization: authToken,
        'Access-Control-Allow-Origin': '*'
      }
    }

    const data = {
      ages: [this.state.ageOne, this.state.ageTwo],
      currencyId: this.state.currencyId,
      startDate: this.state.startDate,
      endDate: this.state.endDate 
    }

    axios.post(`http://localhost:8080/api/quotation`, data, config)
    .then(res => {
      console.log(res.data)
      alert('total: ' + res.data.total + '\ncurrencyId: ' + res.data.currencyId + '\nquotationId: ' + res.data.quotationId);
    })
  }

  handleCurrChange(event) {
    this.setState({currencyId: event.target.value});
  }

  handleAgeOneChange(event) {
    this.setState({ageOne: event.target.value});
 }

 handleAgeTwoChange(event) {
  this.setState({ageTwo: event.target.value});
}

 handleStartDateChange(event) {
   console.log(event.target.value)
  this.setState({startDate: event.target.value});
}

handleEndDateChange(event) {
  this.setState({endDate: event.target.value});
}

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          <p>Person 1 age:</p>
        <input
          type="text"
          value={this.state.ageOne} 
          onChange={this.handleAgeOneChange} 
        />

        <p>Person 2 age:</p>
        <input
          type="text"
          value={this.state.ageTwo} 
          onChange={this.handleAgeTwoChange} 
        />

          currency:
          <select value={this.state.currencyId} onChange={this.handleCurrChange}>
            <option value="EUR">EUR</option>
            <option value="AUD">AUD</option>
            <option value="USD">USD</option>
            <option value="PAB">PAB</option>
          </select>

          <p>Start Date:</p>
        <input
          type="text"
          value={this.state.startDate} 
          onChange={this.handleStartDateChange} 
        />

      <p>End Date:</p>
        <input
          type="text"
          value={this.state.endDate} 
          onChange={this.handleEndDateChange} 
        />
        </label>
        <input type="submit" value="Submit" />
      </form>
    );
  } 
}
