package controller;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView view;

	/** The model. */
	private IModel model;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view  the view
	 * @param model the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);

	}

	/**
	 * Control.
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("Welcome to BoulderBash ! Press any key to start the game. Press 1, 2, 3, 4 or 5 to change the map. Use Z/Q/S/D to move the character.");
	}
	
	public void start() {
		
		while(true) {
			this.model.loop();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the view.
	 *
	 * @param pview the new view
	 */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Order perform.
	 *
	 * @param controllerOrder the controller order
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {
		

		
			switch (controllerOrder) {
			case Map1:
				this.model.loadMap(1);
				break;
			case Map2:
				this.model.loadMap(2);
				break;
			case Map3:
				this.model.loadMap(3);
				break;
			case Map4:
				this.model.loadMap(4);
				break;
			case Map5:
				this.model.loadMap(5);
				break;
			case Z:
				this.model.getMap().getPlayer().movePlayer('Z');
				this.model.modelNotify();
				break;
			case Q:
				this.model.getMap().getPlayer().movePlayer('Q');
				this.model.modelNotify();
				break;
			case S:
				this.model.getMap().getPlayer().movePlayer('S');
				this.model.modelNotify();
				break;
			case D:
				this.model.getMap().getPlayer().movePlayer('D');
				this.model.modelNotify();
				break;
			default:
				break;
			}
			
		}
	

}
