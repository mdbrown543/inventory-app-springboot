import React, { useState } from "react";

import apiURL from "../api";

export function Items(props) {
  const [nameClicked, setNameClicked] = useState(false);
  const [title, setTitle] = useState(props.item.title);
  const [description, setDescription] = useState(props.item.description);
  const [category, setCategory] = useState(props.item.category);
  const [image, setImage] = useState(props.item.image);
  const [price, setPrice] = useState(props.item.price);

  // Delete an item using ID
  const handleDelete = async () => {
    const res = await fetch(`${apiURL}/items/${props.item.id}`, {
      method: "DELETE",
    });
    const data = await res.json();

    props.setUpdateItem(!props.updateItem);
    setNameClicked(false);
    window.location.reload(false);
  };

  const handleUpdate = async () => {
    const res = await fetch(`${apiURL}/items/${props.item.id}`, {
      method: "PUT",
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

    const data = await res.json();
    props.setItems(data);
    props.setUpdateItem(!props.updateItem);
  };

  const itemData = (
    <div>
      <div>
      <img src={image} className="rounded img-fluid card-img-top" alt={title} />
      </div>

      <div className="card-body">
      <form onSubmit={handleUpdate}>
        <div className="title">
          <label htmlFor={title}>Title</label>
          <input
            type="text"
            id={title}
            value={title}
            onChange={(e) => {
              setTitle(e.target.value);
            }}
            placeholder="Title"
            required
          />
        </div>
        <div className="text">
        <label htmlFor={description}>Description</label>
          <textarea rows="5"
            type="textarea"
            id={description}
            value={description}
            onChange={(e) => {
              setDescription(e.target.value);
            }}
            placeholder="Description"
            required
            />
        </div>
        <div className="card-text">
        <label htmlFor={props.item.id}>Category</label>
          <input
            type="text"
            id={props.item.id}
            value={category}
            onChange={(e) => {
              setCategory(e.target.value);
            }}
            placeholder="Category"
            required
          />
        </div>
        <div className="card-text">
        <label htmlFor={image}>Image URL</label>
          <input
            type="text"
            id={image}
            value={image}
            onChange={(e) => {
              setImage(e.target.value);
            }}
            placeholder="image.jpg"
            required
          />
        </div>

        <div className="card-text">
        <label htmlFor={price}>Price</label>
          <input
            type="number"
            id={price}
            min="0.00"
            step="0.01"
            value={price}
            onChange={(e) => {
              setPrice(e.target.value);
            }}
            placeholder="0.00"
            required
          />
        </div>
        <button type="submit" className="btn btn-success my-2 mx-2">
          Update Item
        </button>

        <button type="button" className="btn btn-danger my-2 mx-2" onClick={handleDelete}>
        DELETE
      </button>
      </form>
      </div>
    </div>
  );

  return (
    <>
      <h3>
        <button
          type="button"
          className="btn btn-primary title-button"
          onClick={() => {
            setNameClicked(!nameClicked);
          }}
        >
          {title}
        </button>
      </h3>
      {nameClicked && itemData}
    </>
  );
}
