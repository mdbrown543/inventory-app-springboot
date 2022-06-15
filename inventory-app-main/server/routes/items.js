const express = require("express");
const router = express.Router();
const { Item } = require("../models");

// Get a single item by ID
router.get("/:id", async (req, res, next) => {
  const item = await Item.findByPk(req.params.id);
  res.json(item);
});

// GET /item -- get all items
router.get("/", async (req, res, next) => {
  try {
    const items = await Item.findAll();
    res.json(items);
  } catch (error) {
    next(error);
  }
});

router.delete("/:id", async (req, res, next) => {
  try {
    const items = await Item.destroy({
      where: {
        id: req.params.id,
      },
    });

    const updatedItem = await Item.findAll();
    res.json(updatedItem);
  } catch (error) {
    next(error);
  }
});
// start of presentation
router.post("/", async (req, res, next) => {
  try {
    const newItem = await Item.create(req.body);
    res.json(newItem);
  } catch (error) {
    next(error);
  }
});
// end of presentation

router.put("/:id", async (req, res, next) => {
  try {
    const newItem = await Item.update(req.body, {
      where: {
        id: req.params.id,
      },
      returning: true,
    });
    res.json(newItem);
  } catch (error) {
    next(error);
  }
});

module.exports = router;
