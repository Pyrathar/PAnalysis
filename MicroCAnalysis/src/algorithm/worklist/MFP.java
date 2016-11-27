package algorithm.worklist;

import java.util.ArrayList;
import java.util.List;

public class MFP {
	
	private List<MFPElement>[] circle;
	private List<MFPElement>[] bullet;
	
	@SuppressWarnings("unchecked")
	public MFP(int numberOfLines){
		this.circle = new List[numberOfLines];
		this.bullet = new List[numberOfLines];
		for (int i = 0; i < circle.length; i++) {
			circle[i] = new ArrayList<MFPElement>();
		}
	}
	
	public void setCircleLabel(int label, List<MFPElement> elements){
		this.circle[label-1] = elements;
	}
	
	public void setBulletLabel(int label, List<MFPElement> elements){
		this.bullet[label-1] = elements;
	}

	public List<MFPElement> getCircle(int label) {
		return circle[label-1];
	}

	public List<MFPElement>[] getCircles() {
		return circle;
	}

	public void setCircle(List<MFPElement>[] circle) {
		this.circle = circle;
	}

	public List<MFPElement> getBullet(int label) {
		return bullet[label-1];
	}

	public List<MFPElement>[] getBullets() {
		return bullet;
	}

	public void setBullet(List<MFPElement>[] bullet) {
		this.bullet = bullet;
	}

}
