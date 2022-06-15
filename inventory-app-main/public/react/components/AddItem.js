import React, { useState } from "react";
import apiURL from "../api";

export function AddItem({ setAddClicked }) {
  // start of presentation
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState("");
  const [image, setImage] = useState("");
  const [price, setPrice] = useState(0);
  

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`${apiURL}/items`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        title: title,
        description: description,
        category: category,
        image: image,
        price: price,
      }),
    });

    setTitle("");
    setDescription("");
    setCategory("");
    setImage("");
    setPrice(0);
    setAddClicked(false);
  };
  // end of presentation

  return (
    <>
      <section id="controls">
        <h3>Add An Item</h3>
        <form className="form-inline" onSubmit={handleSubmit}>
          <input
            type="text"
            className="form-control mb-2 mr-sm-2"
            id="formInput"
            value={title}
            onChange={(e) => {
              setTitle(e.target.value);
            }}
            placeholder="title"
            required
          />
          <input
            type="textarea"
            className="form-control mb-2 mr-sm-2"
            id="formInput"
            value={description}
            onChange={(e) => {
              setDescription(e.target.value);
            }}
            placeholder="description"
            required
          />
          <input
            type="text"
            className="form-control mb-2 mr-sm-2"
            id="formInput"
            value={category}
            onChange={(e) => {
              setCategory(e.target.value);
            }}
            placeholder="category"
            required
          />
          <input
            type="text"
            className="form-control mb-2 mr-sm-2"
            id="formInput"
            value={image}
            onChange={(e) => {
              setImage(e.target.value);
            }}
            placeholder="image"
            required
          />
          <input
            type="number"
            className="form-control mb-2 mr-sm-2"
            id="formInput"
            min="0.00"
            step="0.01"
            value={price}
            onChange={(e) => {
              setPrice(e.target.value);
            }}
            placeholder="price"
            required
          />
          <button type="submit" className="btn btn-success mb-4">
            Submit Item
          </button>
        </form>
      </section>
    </>
  );
}
