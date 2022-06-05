package zibi.robotx.autocad.common.collision.old

import java.util.*

//import org.andresoviedo.android_3d_model_engine.controller.TouchEvent;
//import org.andresoviedo.android_3d_model_engine.model.Object3DData;
//import org.andresoviedo.android_3d_model_engine.objects.Point;
//import org.andresoviedo.android_3d_model_engine.services.SceneLoader;
//import org.andresoviedo.android_3d_model_engine.view.ModelSurfaceView;
//import org.andresoviedo.util.android.AndroidUtils;
//import org.andresoviedo.util.event.EventListener;

class CollisionController(){//view: ModelSurfaceView, scene: SceneLoader) { //implements EventListener {
//    private val view: ModelSurfaceView
//    private val scene: SceneLoader
//    val objects: List<Object3DData>
//    fun addListener(listener: EventListener?) {
//        this.listeners.add(listener)
//    }

    //    @Override
    fun onEvent(event: EventObject?): Boolean {
//        Log.v("CollisionController", "Processing event... " + event.toString());
//        if (event instanceof TouchEvent) {
//            TouchEvent touchEvent = (TouchEvent) event;
//            if (touchEvent.getAction() == TouchEvent.CLICK) {
//        if (objects.isEmpty()) return true
////        Log.v("CollisionController", objects[0].getCurrentDimensions().toString())
//        val x: Float = touchEvent.getX()
//        val y: Float = touchEvent.getY()
////        Log.v("CollisionController", "Testing for collision... (" + objects.size + ") " + x + "," + y)
//        val objectHit = CollisionDetection.getBoxIntersection(objects, view.getWidth(), view.getHeight(), view.getViewMatrix(), view.getProjectionMatrix(), x, y)
//        if (objectHit != null) {
//
//            // intersection point
//            val point3D: Object3DData? = null
//            if (scene.isCollision()) {
////                Log.i("CollisionController", "Collision. Getting triangle intersection... " + objectHit.id)
//                val point = CollisionDetection.getTriangleIntersection(
//                    objectHit, view.getWidth(), view.getHeight(), view.getViewMatrix(), view.getProjectionMatrix(), x, y)
////                if (point != null) {
////                    Log.i("CollisionController", "Drawing intersection point: " + Arrays.toString(point))
//                    //                            point3D = Point.build(point).setColor(new float[]{1.0f, 0f, 0f, 1f});
////                }
//            }
//
////                    final CollisionEvent collisionEvent = new CollisionEvent(this, objectHit, x, y, point3D);
////                    AndroidUtils.fireEvent(listeners, collisionEvent);
//        }
        //            }
//        }
        return true
    }

    //    private final List<EventListener> listeners = new ArrayList<>();
//    init {
//        this.view = view
//        this.scene = scene
//        objects = scene.getObjects()
//    }
}